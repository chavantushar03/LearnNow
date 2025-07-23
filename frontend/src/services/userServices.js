import axios from 'axios'
import {config} from './config'

export async function insertData(firstName, lastName, email, password, dob, userRole){
    try {
    const signupUrl = `${config.serverUrl}/auth/register`
    const body = {
        firstName, lastName, email, password, dob, userRole
    }
    const response = await axios.post(signupUrl, body)
     return {
            status: 'success',
            ...response.data
        };
     } catch (error) {
            console.error("Registration error:", error.response?.data || error.message);
            return { status: "error", message: error.response?.data?.message || "Something went wrong" };
    }
}