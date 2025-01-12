import React from 'react';
import { routerConfig, routes } from './routes';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './utils';
import { AuthProvider } from './contexts/AuthProvider';

export const router = createBrowserRouter(routes, routerConfig);

const App: React.FC = () => {
    return (
        <AuthProvider>
            <RouterProvider router={router} future={{ v7_startTransition: true }} />
        </AuthProvider>
    );
};

export default App;
