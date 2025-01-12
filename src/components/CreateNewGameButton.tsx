import React from 'react';
import { Button } from './ui/button';
import { Card } from './ui/card';
import { post } from '@/lib/axiosHelper';
import { useAuth } from '@/contexts/AuthProvider';

const CreateNewGameButton: React.FC = () => {
    const { user, setGame } = useAuth();

    const handleCreateGame = () => {
        post('/game/start', {
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
                <h2 className="text-2xl font-semibold text-center mb-4">Create a New Game</h2>
                <Button onClick={handleCreateGame} className="w-full mt-4" variant="outline">
                    Create Game
                </Button>
            </Card>
        </div>
    );
};

export default CreateNewGameButton;
