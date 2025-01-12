import React from 'react';
import { createRoot } from 'react-dom/client';
import './scss/index.scss';
import App from './App.tsx';

createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);
