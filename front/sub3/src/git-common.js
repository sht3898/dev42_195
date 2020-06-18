import axios from 'axios'
export default axios.create({
    baseURL: "http://192.168.31.131:8197/api",
    headers: {
       "Content-type": "application/json",
    }
  });