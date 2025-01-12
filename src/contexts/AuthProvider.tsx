import React from 'react';
import { createContext, useContext, useState } from 'react';
import { User } from '@/interfaces/UserInterface';

type GameStatus = 'NEW' | 'IN_PROGRESS' | 'COMPLETED';

export type Game = {
    id: number;
    gameId: string;
    player1: User;
    player2: User | null;
    status: GameStatus;
    board: number[][];
    winner: string;
};

type AuthProviderProps = {
    children: React.ReactNode;
};

type AuthProviderState = {
    user: User | null;
    setUser: (user: User) => void;
    game: Game | null;
    setGame: (game: Game) => void;
};

const AuthProviderContext = createContext<AuthProviderState | undefined>(undefined);

export function AuthProvider({ children }: AuthProviderProps) {
    const [user, setUser] = useState<User | null>(null);
    const [game, setGame] = useState<Game | null>(null);

    return (
        <AuthProviderContext.Provider
            value={{
                user,
                setUser,
                game,
                setGame,
            }}
        >
            {children}
        </AuthProviderContext.Provider>
    );
}

export const useAuth = () => {
    const context = useContext(AuthProviderContext);

    if (context === undefined) throw new Error('useAuth must be used within a AuthProvider');

    return context;
};
