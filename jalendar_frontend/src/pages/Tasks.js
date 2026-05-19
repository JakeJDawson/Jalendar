// Import statements.
import React from "react";

function Tasks({
    tasks,
    title,
    description,
    setTitle,
    setDescription,
    handleSubmit,
    handleDelete,
    handleEdit,
    editingTaskID
}) {
    return (
        <div>
            {/* Simply displays the title of the page. */}
            <h1>Jalendar</h1>

            {/* The input fields and button used for creating new tasks, as
                well as updating currently existing ones. */}
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

                {/* The submit button displays different text, depending on
                    whether the application is currently updating a task, or
                    adding a new one. */}
                <button type="submit">
                    {editingTaskID ? "Update Task" : "Add Task"}
                </button>
            </form>

            {/* Creates the displayed task list. Each task gets its title and
                description shown, as well as a delete and an edit button. */}
            <ul>
                {tasks.map(task => (
                    <li key={task.id}>
                        {task.title} - {task.description}

                        <button onClick={() => handleDelete(task.id)}>
                            Delete
                        </button>

                        <button onClick={() => handleEdit(task)}>
                            Edit
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Tasks;