DELETE FROM game_results;
DELETE FROM game_sessions;
DELETE FROM players;

INSERT INTO players (player_id, user_name, name, password_hash) VALUES
                                                                    (123, 'aUser', 'user', 123),
                                                                    (1234, 'anAwesomeUser', 'awesomeUser', 1234);

INSERT INTO game_sessions (player_id, status, start_time, end_time) VALUES
                                                                        (123, 'ENDED', '2025-01-15T10:00:00', '2025-01-15T10:15:30'),
                                                                        (123, 'ACTIVE', '2025-01-16T14:30:00', NULL),
                                                                        (1234, 'CANCELLED', '2025-01-17T09:00:00', '2025-01-17T09:05:00');

INSERT INTO game_results (game_session_id, outcome, payout) VALUES
                                                                (1, 'WIN', 25.50),
                                                                (3, 'PUSH', 10.00);