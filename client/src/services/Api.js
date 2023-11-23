import axios from 'axios'
import store from '@/store/store'

//For local development use: http://localhost:8081/
const baseUrl = 'https://tgk47x-8081.csb.app/';

export default () => {
  return axios.create({
    baseURL: baseUrl,
    headers: {
      Authorization: `Bearer ${store.state.token}`
    }
  })
}
