import axios from 'axios'
export default axios.create({
    baseURL: "http://192.168.31.131:8197/v1",
    headers: {
       "Content-type": "application/json",
    }
  });