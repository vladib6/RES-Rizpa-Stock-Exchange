import axios from "axios";
const APIPath='http://localhost:8080/'

export default axios.create({
    baseURL:APIPath
});