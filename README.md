# Gaming Subreddit Android App
The Reddit app is an android application that implements infinite scrolling list of the gamming subreddit.
Infinite scrolling is implemented using the the Android Paging Library .
The Rest Api endpoint to load top posts of the gaming subreddit is http://www.reddit.com/r/gaming/top.json

The datasource ensures that 

## Libraries and stack
All Code is written in

[Kotlin]: https://kotlinlang.org	"Kotlin"

[Retrofits Library](https://square.github.io/retrofit/) is used to implement the Rest Api
[Android Paging](https://developer.android.com/topic/libraries/architecture/paging) Library is used for infinite scrolling
Viewmodel and LiveData is used within project to make MainActivity lifecycle aware 



