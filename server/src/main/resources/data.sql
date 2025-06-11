-- Alte Daten löschen
DELETE FROM game_results;
DELETE FROM game_sessions;
DELETE FROM players;

-- Spieler anlegen
INSERT INTO players (player_id, user_name, name, password) VALUES
    (123, 'aUser', 'user', '123');

-- 15 Sessions, alle heute Abend von 19:00 bis 19:20
INSERT INTO game_sessions (id, player_id, status, start_time, end_time) VALUES
                                                                            (1,  123, 'ENDED',    '2025-06-11T19:00:00', '2025-06-11T19:00:00'),
                                                                            (2,  123, 'ENDED',    '2025-06-11T19:01:00', '2025-06-11T19:02:00'),
                                                                            (3,  123, 'ENDED',    '2025-06-11T19:01:00', '2025-06-11T19:02:00'),
                                                                            (4,  123, 'ENDED',    '2025-06-11T19:02:00', '2025-06-11T19:02:00'),
                                                                            (5,  123, 'ENDED',    '2025-06-11T19:02:00', '2025-06-11T19:02:00'),
                                                                            (6,  123, 'ENDED',    '2025-06-11T19:03:00', '2025-06-11T19:03:00'),
                                                                            (7,  123, 'ENDED',    '2025-06-11T19:03:00', '2025-06-11T19:03:00'),
                                                                            (8,  123, 'ENDED',    '2025-06-11T19:03:00', '2025-06-11T19:03:00'),
                                                                            (9,  123, 'ENDED',    '2025-06-11T19:04:00', '2025-06-11T19:04:00'),
                                                                            (10, 123, 'ENDED',    '2025-06-11T19:04:00', '2025-06-11T19:04:00'),
                                                                            (11, 123, 'ENDED',    '2025-06-11T19:04:00', '2025-06-11T19:04:00'),
                                                                            (12, 123, 'ENDED',    '2025-06-11T19:05:00', '2025-06-11T19:05:00'),
                                                                            (13, 123, 'ENDED',    '2025-06-11T19:05:00', '2025-06-11T19:05:00'),
                                                                            (14, 123, 'ENDED',    '2025-06-11T19:06:00', '2025-06-11T19:06:00'),
                                                                            (15, 123, 'ENDED',    '2025-06-11T19:08:00', '2025-06-11T19:08:00');

-- Zu jeder Session genau ein Ergebnis
INSERT INTO game_results (game_result_id, game_session_id, outcome, payout) VALUES
                                                                                (1,  1,  'WIN',  12.50),
                                                                                (2,  2,  'LOSS',  0.00),
                                                                                (3,  3,  'PUSH', 10.00),
                                                                                (4,  4,  'WIN',  18.75),
                                                                                (5,  5,  'LOSS',  0.00),
                                                                                (6,  6,  'WIN',  22.00),
                                                                                (7,  7,  'PUSH', 10.00),
                                                                                (8,  8,  'LOSS',  0.00),
                                                                                (9,  9,  'WIN',  30.00),
                                                                                (10, 10, 'LOSS',  0.00),
                                                                                (11, 11, 'PUSH', 10.00),
                                                                                (12, 12, 'WIN',  15.25),
                                                                                (13, 13, 'LOSS',  0.00),
                                                                                (14, 14, 'WIN',  27.50),
                                                                                (15, 15, 'PUSH', 10.00);
