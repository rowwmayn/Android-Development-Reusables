# KTCounter — Session Handoff (Checkpoint 1)

## Project Info
- **Name:** KTCounter (`com.example.ktcounter`)
- **Separate project from:** zebraone (earlier calculator learning project)
- **IDE:** Android Studio Quali/Quail, version 2026.1 — uses newer Gradle Kotlin DSL syntax (e.g. `compileSdk { version = release(37) }`) that postdates Claude's training; verify syntax via IDE autocomplete or docs when in doubt.
- **SDK config:** minSdk 24, targetSdk 37, compileSdk 37
- **Kotlin:** 2.2.10 | **AGP:** 9.3.2 | **KSP:** 2.2.10-2.0.2 | **Room:** 2.8.4

## Goal
1. One-screen counter app with Room persistence ← **in progress**
2. Add a free image API that swaps the displayed image every 20 increments ← not started

## Directory Structure (standard per-project pattern)

### app/src/main/kotlin+java/com/example/ktcounter/
1. ui/theme/ (Color.kt, Theme.kt, Type.kt)
2.  MainActivity.kt
3. CounterEntity.kt (Room Entity)
4. CounterDao.kt (Room DAO)
5. AppDatabase.kt (Room Database)
res/
### Gradle Scripts/
1. build.gradle.kts (Project + Module :app)
2. gradle.properties
3. gradle-wrapper.properties
4. libs.versions.toml
5. local.properties
6. settings.gradle.kts


## Current State of Code

**`MainActivity.kt`** — contains `Greeting` (unused-but-kept for reference) and `Counter` Composables, both called inside a `Column` in `setContent`. `Counter` currently uses **only** `remember { mutableIntStateOf(0) }` — no Room wiring yet, so count resets on app restart/rotation.

**`CounterEntity.kt`** — Room table definition:
```kotlin
@Entity(tableName = "counter")
data class CounterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val count: Int
)
```
Design decision: single-row table only (not a history log) — always overwrite the same row.

**`CounterDao.kt`** — Rome wrote this mostly independently:
```kotlin
@Dao
interface CounterDao {
    @Upsert
    suspend fun upsertCounter(counter: CounterEntity)

    @Query("SELECT * FROM counter WHERE id = 1")
    suspend fun getCounter(): CounterEntity?
}
```

**`AppDatabase.kt`**:
```kotlin
@Database(entities = [CounterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun counterDao(): CounterDao
}
```

## Dependencies Added This Session
`libs.versions.toml`: added `room = "2.8.4"`, `ksp = "2.2.10-2.0.2"`, three Room library entries, `ksp` plugin entry.
`app/build.gradle.kts`: applied `alias(libs.plugins.ksp)`, added `implementation` for room-runtime/room-ktx, `ksp(...)` for room-compiler.

## Gradle/Build Issues Hit & Resolved This Session
1. **AAR metadata mismatch** (compileSdk 36 vs libraries needing 37) → upgraded `compileSdk` to `release(37)`, installed API 37 SDK platform via SDK Manager.
2. **KSP + AGP 9 "built-in Kotlin" conflict** (`Using kotlin.sourceSets DSL...not allowed`) → added `android.disallowKotlinSourceSets=false` to `gradle.properties` (official documented workaround for current AGP9/KSP compatibility gap).
3. **APK file lock error** (Windows file-in-use) → resolved by just re-running; noted as usually a transient AV/adb lock issue.

## Concepts Covered This Session (understanding: moderate, self-flagged by Rome)
- Composable functions & placement (defined outside/below MainActivity, called inside `setContent`)
- Recomposition & declarative vs. imperative UI
- `remember` + `mutableStateOf`/`mutableIntStateOf` + `by` delegate syntax
- `Modifier` parameter pattern (why Composables accept `modifier: Modifier = Modifier`, chaining)
- `Scaffold` + `innerPadding` (`PaddingValues`) + trailing lambda syntax
- `Column` stacking vs. overlapping when no container is used
- Padding-around-container vs. spacing-between-children distinction
- **Room trio (new this session):** `@Entity` (table def), `@Dao` interface (declared operations Room implements via KSP codegen at compile time), `@Database` abstract class (ties Entity + DAO together, exposes DAO instance)
- Gradle version catalogs (`libs.versions.toml`) — versions/libraries/plugins blocks, `version.ref`
- KSP vs. `implementation` — annotation processors vs. runtime libraries
- Reading/fixing Gradle AAR metadata and AGP/KSP compatibility errors from scratch

## Not Yet Covered (next session should start here)
- **`Context`** — needed to actually construct a working `AppDatabase` instance (`Room.databaseBuilder(context, ...)`)
- **Coroutines / `suspend` function calling** — needed to call `getCounter()`/`upsertCounter()` from UI code without blocking the main thread
- Wiring: read saved count on app start → display it; save new count to Room on each increment
- (Later phase) Free image API integration, image swap every 20 increments

## Rome's Preferences (carry forward)
- No unnecessary artifacts; text/inline code, ask permission first
- Directory-based workflow — Claude should know file locations, give targeted file+snippet edits, not full-file dumps unless needed
- Rome takes own markdown notes; Claude doesn't need to generate note artifacts unprompted
- Explain the *why*, not just the *what* — moderate pacing, check understanding before moving on
- Flag IDE/Gradle DSL syntax uncertainty rather than guess (Quali 2026.1 is post-training-cutoff)