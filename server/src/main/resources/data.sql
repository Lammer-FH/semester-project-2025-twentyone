DELETE FROM game_results;
DELETE FROM game_sessions;

INSERT INTO game_sessions (start_time, end_time) VALUES
                                                     ('2025-01-15T10:00:00', '2025-01-15T10:15:30'),  -- Session 1 (ended)
                                                     ('2025-01-16T14:30:00', NULL),                   -- Session 2 (ongoing)
                                                     ('2025-01-17T09:00:00', '2025-01-17T09:05:00');  -- Session 3 (ended)


INSERT INTO game_results (game_session_id, outcome, payout) VALUES
                                                                (1, 'WIN', 25.50),    -- Result for Session 1
                                                                (3, 'PUSH', 10.00);           -- Result for Session 3
