import React, { useState } from 'react';
import { Button } from './ui/button';
import { Card } from './ui/card';
import { Input } from './ui/input';
import { useAuth } from '@/contexts/AuthProvider';
import { useNavigate } from 'react-router';

const RegisterPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { setUser } = useAuth();

    const navigate = useNavigate();

    const register = () => {
        if (username && password) {
            const url = `http://localhost:8080/api/auth/register?username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`;

            fetch(url, {
                method: 'POST',
                headers: {
                    Accept: '*/*',
                },
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Registration failed');
                    }
                    return response.json();
                })
                .then(data => {
                    setUser(data);
                    navigate('/');
                })
                .catch(e => alert(e.message));
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
                <h2 className="text-2xl font-semibold text-center mb-4">Register</h2>

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
                    <Button onClick={register} className="mt-4" variant="outline">
                        Register
                    </Button>
                    <Button onClick={() => navigate(-1)} className="mt-4" variant="outline">
                        Go Back
                    </Button>
                </div>
            </Card>
        </div>
    );
};

export default RegisterPage;
