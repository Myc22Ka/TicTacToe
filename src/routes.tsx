import React from 'react';
import { RouteObject } from 'react-router';
import HomePage from './components/HomePage';
import LoginPage from './components/LoginPage';
import RegisterPage from './components/RegisterPage';
import GamesList from './components/GameList';
import UsersList from './components/UsersList';

export const routerConfig = {
    future: {
        v7_relativeSplatPath: true,
        v7_fetcherPersist: true,
        v7_normalizeFormMethod: true,
        v7_partialHydration: true,
        v7_skipActionErrorRevalidation: true,
    },
};

type Routes = RouteObject & {
    unauthenticated?: boolean;
};

export const routes: Routes[] = [
    {
        path: '/',
        element: <HomePage />,
    },
    {
        path: '/login',
        element: <LoginPage />,
    },
    {
        path: '/register',
        element: <RegisterPage />,
    },
    {
        path: '/game-list',
        element: <GamesList />,
    },
    {
        path: '/users-list',
        element: <UsersList />,
    },
];
