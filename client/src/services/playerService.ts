import axios from 'axios';

const API_URL = 'http://localhost:8080/twentyone/api';

// Add axios default configuration
axios.defaults.headers.common['Accept'] = 'application/json';
axios.defaults.headers.common['Content-Type'] = 'application/json';

export interface Player {
    id?: number;
    userName: string;
    name: string;
    passwordHash?: string;
}

export interface LoginCredentials {
    userName: string;
    password: string;
}

const handleAxiosError = (error: any) => {
    if (error.code === 'ERR_NETWORK') {
        throw new Error('Cannot connect to the server. Please make sure the server is running and try again.');
    }
    throw error;
};

export const playerService = {
    async getPlayer(id: number): Promise<Player> {
        try {
            const response = await axios.get(`${API_URL}/players/${id}`);
            return response.data;
        } catch (error) {
            handleAxiosError(error);
            throw error;
        }
    },

    async createPlayer(player: Player): Promise<Player> {
        try {
            console.log('Creating player:', player);
            const response = await axios.post(`${API_URL}/players`, player);
            console.log('Create player response:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error creating player:', error);
            handleAxiosError(error);
            throw error;
        }
    },

    async updatePlayer(id: number, player: Player): Promise<Player> {
        try {
            const response = await axios.put(`${API_URL}/players/${id}`, player);
            return response.data;
        } catch (error) {
            handleAxiosError(error);
            throw error;
        }
    },

    async deletePlayer(id: number): Promise<void> {
        // ToDo
    },

    async login(credentials: LoginCredentials): Promise<void> {
        // ToDo
    }
}; 