import axios from 'axios';
const api = axios.create({ baseURL: '/api/students' });
export default api;
