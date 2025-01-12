import React from 'react';
import { Button } from './ui/button';
import { Card } from './ui/card';
import { post } from '@/lib/axiosHelper';
import { useAuth } from '@/contexts/AuthProvider';

const ConnectToRandom: React.FC = () => {
    const { user, setGame } = useAuth();
    const random = () => {
        post('/game/connect/random', {
            id: user?.id,
            login: user?.login,
            password: user?.password,
        })
            .then(response => {
                setGame(response.data);
            })
            .catch(e => alert(e.message));
    };

    return (
        <div className="flex justify-center items-center">
            <Card className="w-80 p-4">
                <h2 className="text-2xl font-semibold text-center mb-4">Connect to random game</h2>
                <Button onClick={random} className="w-full mt-4" variant="outline">
                    Play
                </Button>
            </Card>
        </div>
    );
};

export default ConnectToRandom;
