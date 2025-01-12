import React, { useState } from 'react';
import { Button } from './ui/button';
import { Card } from './ui/card';
import { post } from '@/lib/axiosHelper';
import { useAuth } from '@/contexts/AuthProvider';

const ConnectById: React.FC = () => {
    const [gameId, setGameId] = useState<string>('');
    const { user, setGame } = useAuth();

    const handleCreateGame = () => {
        if (gameId) {
            alert(`Connecting to game with ID: ${gameId}`);
            post('/game/connect', {
                player: {
                    id: user?.id,
                    login: user?.login,
                    password: user?.password,
                },
                gameId: gameId,
            })
                .then(response => setGame(response.data))
                .catch(e => alert(e.message));
        } else {
            alert('Please provide a valid game ID');
        }
    };

    const handleGameIdChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setGameId(e.target.value);
    };

    return (
        <div className="flex justify-center items-center">
            <Card className="w-80 p-4">
                <h2 className="text-2xl font-semibold text-center mb-4">Connect to game</h2>
                <input
                    type="text"
                    value={gameId}
                    onChange={handleGameIdChange}
                    placeholder="Enter game ID"
                    className="mb-4 p-2 w-full border"
                />
                <Button onClick={handleCreateGame} className="w-full mt-4" variant="outline">
                    Play
                </Button>
            </Card>
        </div>
    );
};

export default ConnectById;
