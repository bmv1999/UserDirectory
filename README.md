# UserDirectory App
- Name: Benjamin Vo
- CWID: 889378170
- Course: CPSC 411A Section 2

The UserDirectory App fetches a list of users from an API, saves the users in a Room Database, and displays them offline using Jetpack Compose.

The app loads users from the public JSONPlaceholder API: https://jsonplaceholder.typicode.com/users

## Features
- Fetch users using Retrofit (GET only)
- Cache users locally in Room Database
- Offline support (always shows cached data)
- Jetpack Compose UI
- Search users by name or email
- ViewModel + Flow for reactive updates

## Screenshots
### Initial Load with Interent
<img width="320" height="711" alt="image" src="https://github.com/user-attachments/assets/5b97da00-41c8-480d-a6fd-feeac3354061" />

### Search Function
<img width="320" height="712" alt="image" src="https://github.com/user-attachments/assets/9fc4ad47-9c34-47fe-9b1a-8d3ffebfd4fb" />

### App Load without Interent
<img width="395" height="879" alt="image" src="https://github.com/user-attachments/assets/f9853dcc-c8fc-4396-a696-695df4abbd39" />


