import React from 'react';
import TicTacToeBoard from './TicTacToeBoard';
import { Button } from './ui/button';
import { Link, useNavigate } from 'react-router-dom';
import CreateNewGameButton from './CreateNewGameButton';
import { useAuth } from '@/contexts/AuthProvider';
import ConnectToRandom from './ConnectToRandom';
import ConnectById from './ConnectById';
import GetGameId from './GetGameId';

const HomePage: React.FC = () => {
    const navigate = useNavigate();
    const { user } = useAuth();

    const goToLogin = () => {
        navigate('/login');
    };

    const goToRegister = () => {
        navigate('/register');
    };

    return (
        <div className="flex justify-center items-center h-screen space-x-4">
            <div className="w-1/4 p-4 h-full">
                <div className="flex flex-col items-center space-y-4">
                    <h2 className="text-2xl font-semibold mb-4">Welcome</h2>
                    {user?.login && <p className="text-xl font-semibold">Logged in as: {user?.login}</p>}

                    <div className="space-y-4 mt-4 w-full">
                        <Button onClick={goToLogin} variant="outline" className="w-full">
                            Go to Login
                        </Button>
                        <Button onClick={goToRegister} variant="outline" className="w-full">
                            Go to Register
                        </Button>
                        <div className="flex gap-2">
                            <Button variant="outline">
                                <Link to="/game-list">Game List</Link>
                            </Button>
                            <Button variant="outline">
                                <Link to="/users-list">User List</Link>
                            </Button>
                        </div>
                        {user && (
                            <>
                                <CreateNewGameButton />
                                <ConnectToRandom />
                                <ConnectById />
                                <GetGameId />
                            </>
                        )}
                    </div>
                </div>
            </div>

            <div className="flex-1 p-4">
                <TicTacToeBoard />
            </div>
        </div>
    );
};

export default HomePage;
