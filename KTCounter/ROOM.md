# ROOM Database

Room is a persistence library that provides an easy way to store and manage local data using SQLite.
Previously SQLite was used directly but ROOM eventually makes everything easier albeit with a bit steep learning curve.

## There are a few concepts

### 1. Entity
Represents a table in the database.
### 2. DAO Database Access Object
Basically the middleman between the UI and the Database. UI doesn't have direct connection to the database rather it acts as the middleman. UI requests data, DAO talks to the database, database gives it back to DAO and then DAO returns it back to the UI.

#### MVVM
**Model:** Represents the data and logic.
**View:** The activities or the fragments.
**ViewModel:** The middleman acting between view and model. In our case this is the DAO. View model can survive configuaration changes.

**ROOM requires ROOM and KSP dependencies. Simply search them on the web and copy paste inside your build files.**


### @Entity
Means that the data class with this annotation would be considered as a table in our database.
`
@Entity(tableName = "Task")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val taskName:String,
    val isDone: Boolean = false
)
`
### Dao (Data Access Object)
