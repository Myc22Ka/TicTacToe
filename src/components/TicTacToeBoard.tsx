import React from 'react';
import { Card } from './ui/card';
import { useAuth } from '@/contexts/AuthProvider';
import { post } from '@/lib/axiosHelper';

const TicTacToeBoard: React.FC = () => {
    const { game, setGame } = useAuth();

    const makeMove = (row: number, col: number) => {
        if (game?.winner) {
            alert(`Game over! Winner: ${game.winner}`);
            return;
        }

        if (game?.board[row][col] !== 0) {
            alert('Cell already occupied!');
            return;
        }

        post('/game/gameplay', {
            type: game.board.flat(1).filter(n => n !== 0).length % 2 ? 'X' : 'O',
            coordinateX: row,
            coordinateY: col,
            gameId: game?.gameId,
        })
            .then(response => setGame(response.data))
            .catch(e => alert(e.message));
    };

    return (
        <div className="flex flex-col items-center justify-center h-screen space-y-4">
            {game?.board && (
                <Card className="p-4 w-[600px]">
                    <h2 className="text-2xl font-semibold text-center mb-4">Tic Tac Toe</h2>
                    <div className="grid grid-cols-3 gap-1">
                        {game?.board.map((row, rowIndex) =>
                            row.map((cell, colIndex) => (
                                <div
                                    onClick={() => makeMove(rowIndex, colIndex)}
                                    key={`${rowIndex}-${colIndex}`}
                                    className="flex justify-center items-center w-32 h-32 border-2 border-gray-300 text-4xl font-bold cursor-pointer"
                                    style={{
                                        backgroundColor: cell === 1 ? '#d1e7ff' : cell === 2 ? '#ffe4e1' : '#fff',
                                    }}
                                >
                                    {cell !== 0 ? (cell === 1 ? 'X' : '0') : null}
                                </div>
                            ))
                        )}
                    </div>
                </Card>
            )}
        </div>
    );
};

export default TicTacToeBoard;
