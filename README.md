



# Gaming Subreddit Android App

The Reddit app is an android application that implements infinite scrolling list of the gamming subreddit.
Infinite scrolling is implemented using the the Android Paging Library .
The Rest Api endpoint to load top posts of the gaming subreddit is http://www.reddit.com/r/gaming/top.json

Each item in the list has a title, score and subreddit.

When item is clicked, it opens up the details in a browser.

## Libraries and stack


All Code is written in [Koltin](https://kotlinlang.org)

[Retrofits Library](https://square.github.io/retrofit/) is used to implement the Rest Api

[Android Paging](https://developer.android.com/topic/libraries/architecture/paging) Library is used for infinite scrolling

## Implementation Details

As mentioned above, the application is implemented using the android paging library for infinite scrolling, livedata and viewmodel for the MainActivity to be lifecycle aware

Viewmodel and LiveData is used within project to make MainActivity lifecycle aware . The data source class is implemented such that 25 items are requested from the API

 when the MainActivity is launched.

Using the **after** and limit  parameter of the reddit api, 25 more items are loaded when the bottom of the recyclerview is reached using the.

The MainActivity Recyclerview is implemented such that in portrait mode for Phone devices, it uses a [LinearLayout Manager](https://developer.android.com/reference/android/support/v7/widget/LinearLayoutManager) to display a vertical list of posts while for Phones in landscape mode and tablets,

a [StaggeredGrid Layout Manager](https://developer.android.com/reference/android/support/v7/widget/StaggeredGridLayoutManager) with spanCount of 2 is used.







## Screens

![Splash Screen](/home/badmus/AndroidStudioProjects/GameSubReddit/images/splash.png)





##  MainActivity On Phone









![MainActivity](/home/badmus/AndroidStudioProjects/GameSubReddit/images/portrait.png)



## MainActivity Landscape

![MainaActivity Landscape](/home/badmus/AndroidStudioProjects/GameSubReddit/images/landscape.png)





## MainActivity on Tablet

![MainActivity Tablet](/home/badmus/AndroidStudioProjects/GameSubReddit/images/tablet.png)





## Browser Intent





![Browser](/home/badmus/AndroidStudioProjects/GameSubReddit/images/browser.png)