import { Separator } from '@radix-ui/react-separator';
import React, { useEffect, useState } from 'react';
import { Button } from './ui/button';
import { Card } from './ui/card';
import { User } from '@/interfaces/UserInterface';
import { get } from '@/lib/axiosHelper';

const UsersList: React.FC = () => {
    const [users, setUsers] = useState<User[] | null>(null);

    useEffect(() => {
        get('/users/all')
            .then(response => setUsers(response.data))
            .catch(e => alert(e.message));
    }, []);

    return (
        <div className="flex justify-center items-center h-screen">
            <Card className="w-full max-w-4xl p-8 space-y-6 shadow-lg bg-gray-100">
                <h2 className="text-3xl font-bold text-center text-gray-800">User List</h2>
                <Separator className="bg-gray-300" />
                {users && users.length > 0 ? (
                    users.map(user => (
                        <div
                            key={user.id}
                            className="mb-6 p-4 border rounded-lg bg-white shadow-md hover:shadow-lg transition-shadow"
                        >
                            <p className="font-semibold text-lg text-gray-700">User ID: {user.id}</p>
                            <p className="text-gray-600">
                                Username: <span className="font-medium text-gray-800">{user.login}</span>
                            </p>
                            <p className="text-gray-600">
                                Password Hash:{' '}
                                <span className="font-mono text-gray-500 break-words">{user.password}</span>
                            </p>
                            <Button
                                variant="outline"
                                className="mt-4 w-full text-gray-700 border-gray-300 hover:bg-gray-200"
                            >
                                View Profile
                            </Button>
                        </div>
                    ))
                ) : (
                    <p className="text-center text-gray-500">No users available</p>
                )}
            </Card>
        </div>
    );
};

export default UsersList;
