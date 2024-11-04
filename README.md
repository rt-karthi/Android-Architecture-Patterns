<h2>Android Architecture Patterns</h2>
This Repository consist of different types of android architecture
<dl>
  <dt><b>1. MVC (Model-View-Controller)</b></dt>
    <dd><li>Model: Manages the data and business logic of the app.</li></dd>
    <dd><li>View: Displays the data to the user and handles UI-related code.</li></dd>
    <dd><li>Controller: Acts as a bridge between the Model and View, handling input and updating the Model or View as needed.</li></dd>
</dl>
    
<br>

<dl>
   <dt><b>2. MVP (Model-View-Presenter)</b></dt>
   <dd><li>Model: Same as in MVC; holds data and business logic.</li></dd>
   <dd><li>View: Interface for rendering data on the screen.</li></dd>
   <dd><li>Presenter: Acts as the middle layer, retrieving data from the Model and updating the View. It doesn’t depend on the Android framework, making it more testable.</li></dd>
</dl>

<br>

<dl>
  <dt><b>3. MVVM (Model-View-ViewModel)</b></dt>
  <dd><li>Model: Manages data and business logic.</li></dd>
  <dd><li>View: Displays data and observes the ViewModel.</li></dd>
  <dd><li>ViewModel: Holds the data and business logic specifically for the UI. In Android, often used with LiveData or StateFlow to observe data changes, reducing tight coupling.</li></dd>
</dl>

<br>

<dl>
  <dt><b>4. Clean Architecture</b></dt>
<li>A layered architecture designed to keep code loosely coupled and highly testable. It often uses a combination of other architectures (like MVVM) within its layers.</li>
 <dd><li>Layers:</b><dt>
  <dd><li>Presentation Layer: UI logic, often using MVP or MVVM.</li></dd>
  <dd><li>Domain Layer: Holds business logic, often with use cases (or interactors) that encapsulate specific application features.</li></dd>
  <dd><li>Data Layer: Manages data sources (e.g., network, database).</li></dd>
</dl>

<br>

<b>Choosing the Right Architecture</b>
  <li><b>MVC: </b>Suitable for simple applications, but may become unmanageable as complexity grows.</li>
  <li><b>MVP: </b>Good for medium-sized projects, where testability and separation of UI code are important.</li>
  <li><b>MVVM: </b>Ideal for reactive programming and modern Android development (often used with Jetpack libraries).</li>
  <li><b>Clean Architecture: </b>Best for large, complex applications that demand high modularity and testability, though it may be overkill for small apps.</li>
