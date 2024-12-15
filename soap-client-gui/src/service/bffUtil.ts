import axios from 'axios';
import {BffResponse} from "../type/BffInterface.ts";

const API_URL = import.meta.env.VITE_API_URL || '';
const TIMEOUT = parseInt(import.meta.env.VITE_TIMEOUT || '10000', 10);


export const uploadFile = async (file: File): Promise<BffResponse> => {
    const formData = new FormData();
    formData.append('file', file);

    try {
        const response = await axios.post(API_URL, formData, {
            headers: { 'Content-Type': 'multipart/form-data' },
            timeout: TIMEOUT,
        });
        return response.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.code === 'ECONNABORTED') {
            throw new Error('Request timeout. Please try again.');
        }
        throw error;
    }
};