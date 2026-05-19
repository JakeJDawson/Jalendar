import{useEffect, useState} from "react";

function App() {
  const[tasks, setTasks] = useState([]);
  const[title, setTitle] = useState("");
  const[description, setDescription] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/tasks?userID=1")
      .then(res => res.json())
      .then(data => {
        setTasks(data)
      });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    const userID = 1;

    fetch(`http://localhost:8080/api/tasks?userID=${userID}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        title: title,
        description: description
      })
    }).then(() => {
      setTitle("");
      setDescription("");
    });
  };

  return(
    <div className="App">
      <h1>Jalendar</h1>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />

        <input
          type="text"
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <button type="submit">Add Task</button>
      </form>

      <ul>
        {tasks.map(task => (
          <li key={task.id}>
            {task.title} - {task.description}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
