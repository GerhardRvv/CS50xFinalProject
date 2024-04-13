#  TwitterSearch
#### Video Demo:  <URL HERE>
#### Description:

- The app purpose is to search Twitter User by screen name, by using Twitter public API
- and then display user details and last tweet.

## Features (How to Use)

- At the `User Search Screen`:
- The user should enter the `Twitter's Screen Name` to search for.
- Once the input field is not empty,`Seach` button is enable ans can be used.
- If the app is able to find and existing user, it will open `User Details` screen.
- The screen will contain the user details information and card with that user `Last Tweet` if any.
- At the `User Search` screen, the user can going back will bring the user back to the `Search User`
- screen.
- User search screen will also contain a list of the 10 recent search, this list is persistent only
- during on e session.

#### Architecture
- The app is build using MVVM + UseCase + Repository Architecture, using Coroutines to run Retrofit
- to fetch data from BE endpoints located at `ApiTwitterService`, then serialize and mapped it
- into Data models to extract only the information required by the UI.

#### UI
- The Ui is rendered using Jetpack Compose with the use of a uiState data class to determine
- the components needed to be displayed.

#### Design System
- Design system components are located at `core >> component`. This directory is meant to be used to
- save reusable composable components.
- Design System theme `TwitterAppTheme` is located at `ui >> theme` this custom theme can be easily access
- through the app to apply pre-selected patters such as `TwitterAppTheme.colors` and
- `TwitterAppTheme.typography`
- Custom theme also "select" the changes to the UI colors to match `Light/Dark Mode`

#### Navigation
- Jetpack Navigation graph handles navigation between different fragments.

#### Event
- SingleLiveEvent used to handling events from user interaction or business logic.

#### Util
- Utils `utils` directory, contain utility classes and testing data objects.
- Extension utilities `UtilExtensions` class with functions to simplify and reuse value transformations.
```
  +--------------------------------------------------------+
  |                       View Layer                       |
  |                                                        |
  |                 +------------------------+             |
  |                 |    Activity/Fragment   |             |
  |                 +------------------------+             |
  |                 |    ViewModel instance  |             |
  |                 +------------------------+             |
  |                 |    LiveData instances  |             |
  |                 +------------------------+             |
  |                                                        |
  +--------------------------------|-----------------------+
  |
  |
  +--------------------------------|-------------------------------+
  |                 |                              |               |
  |          ViewModel Layer               Repository Layer        |
  |                 |                              |               |
  |    +------------------------+      +------------------------+  |
  |    |         ViewModel      |      |        Repository      |  |
  |    +------------------------+      +------------------------+  |
  |    |          Model         |      |         Model          |  |
  |    +------------------------+      +------------------------+  |
  |    |   Repository instances |      |    Remote Data Source  |  |
  |    +------------------------+      +------------------------+  |
  |                 ^                               ^              |
  |                 |                               |              |                         
  +-----------------|-------------------------------|--------------+
  |                 |                               |              |
  |            Model Layer                   Data Source Layer     |
  |                 |                               |              |
  |    +------------------------+      +------------------------+  |
  |    |          Model         |      |   Remote Data Source   |  |
  |    +------------------------+      +------------------------+  |
  |                                                                |
  +----------------------------------------------------------------+
```
# Build

- The app contains the usual `debug` and `release` build variants.
- Select `debug` to be able to launch into an emulator or test device.

### Project Structure and Files' Functions

## Core Architecture
This section outlines the core components of the application, including the data models, networking, and UI components.

### `api/model`
- `Tweet.kt` - Defines the data model for a tweet.
- `TweetResponse.kt` - Contains the structure of the response received from the Twitter API when fetching tweets.
- `TwitterUser.kt` - Defines the data model for a Twitter user's information.
- `TwitterUserResponse.kt` - Contains the structure of the response received from the Twitter API when fetching user data.
- `ApiTwitterService.kt` - Defines the interface for Retrofit to communicate with Twitter's API endpoints.
- `AuthInterceptor.kt` - Adds authentication headers to HTTP requests via Retrofit.

### `component`
- `CtaCustomButton.kt` - Custom 'Call to Action' button component.
- `CustomOutlinedTextField.kt` - Custom text field with an outline, styled according to the app's theme.
- `TwitterCardComponent.kt` - Renders a card view for a tweet in the UI.

### `mapper`
- `Mapper.kt` - Contains interface for mapping one object to another.
- `TweetMapper.kt` - Handles the mapping of tweet data from a response model to a UI model.
- `TwitterUserMapper.kt` - Handles the mapping of Twitter user data from a response model to a UI model.

### `utils`
- `Resource.kt` - Utility class that wraps a response in a Resource class to manage status: success, error, and loading.
- `SingleLiveEvent.kt` - LiveData class for events that emit once.
- `TwitterDataTestUtil.kt` - Provides fake Twitter data composable UI previews.
- `UtilExtensions.kt` - Extension functions for additional reusable functionalities to existing classes.

### `core`
Central directory for core functionalities and classes related to the app's infrastructure.

## Dependency Injection (DI)
Provides dependency injection configurations for the app components.

### `di`
- `CoroutineDispatchersModule.kt` - Configurations for coroutine dispatchers.
- `NetworkModule.kt` - Configures network-related dependencies like Retrofit instances.

## Feature: Search
Components related to the search functionality within the app.

### `search/fragment`
- `SearchUserFragment.kt` - Screen where users can search for Twitter users.

### `search/repository`
- `UserSearchRepository.kt` - Handles data operations for user search, such as network requests.

### `search/ui`
- `TwitterUserSearchScreen.kt` - UI Main component showing the search functionality.

### `search/usecase`
- `SearchTwitterUseCase.kt` - Business logic for searching Twitter users.

### `search/viewmodel`
- `SearchUserViewModel.kt` - Manages the data for the user search screen, and user interactions.

## Theming
Defines the visual theme of the application.

### `theme`
- `Shape.kt` - Definitions for shape theming used throughout the app.
- `TwitterAppColor.kt` - Color palette for the app's theme.
- `TwitterAppTheme.kt` - Theme definition for the app.
- `TwitterAppTypography.kt` - Typography settings like font sizes and styles.

## Feature: Tweet
Components related to the tweet and user details functionality within the app.

### `tweet/fragment`
- `UserDetailsFragment.kt` - Displays details about a specific Twitter user.

### `tweet/repository`
- `TwitterTweetRepository.kt` - Handles data operations for tweets.

### `tweet/ui`
- `UserDetailsScreen.kt` - Presents the user details and last tweet in the UI.

### `tweet/usecase`
- `GetTweetsUseCase.kt` - Business logic to get tweets.

### `tweet/viewmodel`
- `TweetsViewModel.kt` - Provides tweet-related data to the UI and user interactions.

## Application Entry Points
Primary starting points of the application.

- `MainActivity.kt` - Main activity of the app, the UI entry point.
- `TwitterSearchApplication.kt` - Main application class for global configuration.
