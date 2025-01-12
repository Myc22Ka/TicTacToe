/* eslint-disable no-magic-numbers */
import axios, { Method } from 'axios';

export enum ServerResponseCode {
    SUCCESS = 200,
    CREATED = 201,
    BAD_REQUEST = 400,
    UNAUTHORIZED = 401,
    FORBIDDEN = 403,
    NOT_FOUND = 404,
    INTERNAL_SERVER_ERROR = 500,
}

axios.defaults.baseURL = `http://localhost:8080`;
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const OAuth2 = (service: string) => {
    window.location.href = `http://${import.meta.env.VITE_PLATFORM_URL}:${import.meta.env.VITE_BACKEND_PORT}/oauth2/authorization/${service.toLowerCase()}`;
};

export interface RequestConfig {
    method: Method;
    url: string;
    data?: unknown;
    params?: unknown;
}

export const request = ({ method, url, data, params }: RequestConfig) => {
    return axios({
        method,
        url,
        paramsSerializer: {
            indexes: null,
        },
        ...(method === 'GET' ? { params } : { data }),
    });
};

// Helper functions for common HTTP methods
export const get = (url: string, params?: unknown) => request({ method: 'GET', url, params });
export const post = (url: string, data?: unknown) => request({ method: 'POST', url, data });
export const put = (url: string, data?: unknown) => request({ method: 'PUT', url, data });
export const del = (url: string, data?: unknown) => request({ method: 'DELETE', url, data });
