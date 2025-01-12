import React, { useEffect, useState } from 'react';
import { Card } from './ui/card';
import { Button } from './ui/button';
import { Skeleton } from './ui/skeleton';
import { Separator } from './ui/separator';
import { Game } from '@/contexts/AuthProvider';
import { get } from '@/lib/axiosHelper';

const GamesList: React.FC = () => {
    const [games, setGames] = useState<Game[]>([]);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        setLoading(true);
        get('/game/all')
            .then(response => {
                setGames(response.data);
            })
            .catch(e => alert(e.message))
            .finally(() => setLoading(false));
    }, []);

    if (loading) {
        return (
            <div className="flex justify-center items-center h-screen">
                <Skeleton className="w-64 h-10" />
            </div>
        );
    }

    return (
        <div className="flex justify-center items-center">
            <Card className="w-full max-w-4xl p-8 space-y-6 shadow-lg bg-gray-100">
                <h2 className="text-3xl font-bold text-center text-gray-800">Game List</h2>
                <Separator className="bg-gray-300" />
                {games.length > 0 ? (
                    games.map(game => (
                        <div
                            key={game.id}
                            className="mb-6 p-4 border rounded-lg bg-white shadow-md hover:shadow-lg transition-shadow"
                        >
                            <p className="font-semibold text-lg text-gray-700">Game ID: {game.gameId}</p>
                            <p className="text-gray-600">
                                Player 1: <span className="font-medium text-gray-800">{game.player1?.login}</span>
                            </p>
                            <p className="text-gray-600">
                                Player 2:{' '}
                                <span className="font-medium text-gray-800">
                                    {game.player2 ? game.player2.login : 'Waiting for opponent'}
                                </span>
                            </p>
                            <p className="text-gray-600">
                                Status: <span className="text-gray-800 font-medium">{game.status}</span>
                            </p>
                            <p className="text-gray-600">
                                Winner:{' '}
                                <span className="font-medium text-gray-800">
                                    {game.winner ? game.winner : 'No winner yet'}
                                </span>
                            </p>
                            <Button
                                variant="outline"
                                className="mt-4 w-full text-gray-700 border-gray-300 hover:bg-gray-200"
                            >
                                View Board
                            </Button>
                        </div>
                    ))
                ) : (
                    <p className="text-center text-gray-500">No games available</p>
                )}
            </Card>
        </div>
    );
};

export default GamesList;
