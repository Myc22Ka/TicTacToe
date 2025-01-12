import React from 'react';
import { Card } from './ui/card';
import { Button } from './ui/button';
import { useAuth } from '@/contexts/AuthProvider';

const GetGameId: React.FC = () => {
    const { game } = useAuth();

    const handleCopyToClipboard = async () => {
        try {
            await navigator.clipboard.writeText(game?.gameId ?? '');
            alert('Game ID copied to clipboard!');
        } catch {
            alert('Failed to copy game ID.');
        }
    };

    return (
        <div className="flex justify-center items-center">
            <Card className="w-80 p-4">
                <Button onClick={handleCopyToClipboard} className="w-full mt-4" variant="outline">
                    Copy Game ID
                </Button>
            </Card>
        </div>
    );
};

export default GetGameId;
