import axios from "axios";
const APIPath='http://localhost:8080/Web-App_Web/'

export default axios.create({
    baseURL:APIPath
});