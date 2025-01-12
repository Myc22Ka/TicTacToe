import React, { useState } from 'react';
import { Button } from './ui/button';
import { useNavigate } from 'react-router-dom';
import { Card } from './ui/card';
import { Input } from './ui/input';
import { post } from '@/lib/axiosHelper';
import { useAuth } from '@/contexts/AuthProvider';

const LoginPage: React.FC = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { setUser } = useAuth();

    const navigate = useNavigate();

    const handleLogin = () => {
        // Here you can add your login logic (authentication API call, etc.)
        if (username && password) {
            post('/api/auth/login', {
                login: username,
                password: password,
            })
                .then(response => {
                    setUser(response.data);
                })
                .catch(e => alert(e.message));
            navigate('/');
        } else {
            alert('Please enter both username and password');
        }
    };

    const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    };

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    };

    return (
        <div className="flex justify-center items-center h-screen">
            <Card className="w-80 p-4">
                <h2 className="text-2xl font-semibold text-center mb-4">Login</h2>

                {/* Username input */}
                <Input
                    value={username}
                    onChange={handleUsernameChange}
                    placeholder="Enter your username"
                    className="mb-4"
                />

                {/* Password input */}
                <Input
                    value={password}
                    onChange={handlePasswordChange}
                    placeholder="Enter your password"
                    type="password"
                    className="mb-4"
                />

                <div className="flex justify-between">
                    <Button onClick={handleLogin} className="mt-4" variant="outline">
                        Login
                    </Button>
                    <Button onClick={() => navigate(-1)} className="mt-4" variant="outline">
                        Go Back
                    </Button>
                </div>
            </Card>
        </div>
    );
};

export default LoginPage;
