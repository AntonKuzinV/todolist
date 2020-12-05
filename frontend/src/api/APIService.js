import axios from 'axios';

const URL = "http://localhost:8080";

export class APIService {
  constructor() {
  }

  getAllTasks() {
    return axios.get(URL + "/api/tasks").then(response => response.data)
  }

  deleteTask(id) {
    return axios.delete(URL + "/api/tasks/" + id)
  }
  updateTask(item) {
    return axios.put(URL + "/api/tasks/" + item.id, item)
  }

  createTask(priority, text) {
    return axios.post(URL + "/api/tasks/", {
      priority: priority,
      text: text,
      status: 0
    }).then(response => response.data)
  }

}
