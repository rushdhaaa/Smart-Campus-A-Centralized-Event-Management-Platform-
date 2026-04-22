-- Sample Data for Smart Campus Event Management System
INSERT INTO events (title, description, event_date, event_time, venue, department, event_type, capacity, registered_count, status)
VALUES
    ('Tech Summit 2026',
     'Annual technology conference featuring AI, ML and Cloud Computing workshops by industry leaders.',
     '2026-08-15', '09:00', 'Auditorium A', 'Computer Science', 'CONFERENCE', 200, 45, 'UPCOMING'),

    ('Hackathon: Code for Future',
     '24-hour hackathon to solve real-world problems using technology. Form teams of 2-4 students.',
     '2026-08-22', '08:00', 'Innovation Lab', 'Computer Science', 'HACKATHON', 100, 32, 'UPCOMING'),

    ('Cultural Fiesta',
     'Annual cultural festival celebrating diversity with music, dance, food and art from across India.',
     '2026-09-05', '17:00', 'Open Amphitheater', 'Cultural Committee', 'CULTURAL', 500, 210, 'UPCOMING'),

    ('Data Science Workshop',
     'Hands-on workshop on Python, Pandas, and Machine Learning. Bring your laptops!',
     '2026-09-10', '10:00', 'Lab Block 3', 'Computer Science', 'WORKSHOP', 60, 58, 'UPCOMING'),

    ('Career Fair 2026',
     'Top companies recruiting final year students. Bring updated resumes and formal attire required.',
     '2026-09-18', '09:00', 'Sports Complex', 'Placement Cell', 'CAREER', 400, 180, 'UPCOMING'),

    ('Photography Exhibition',
     'Student photography showcase featuring campus life, nature, and street photography.',
     '2026-09-25', '11:00', 'Gallery Hall', 'Arts Club', 'EXHIBITION', 150, 40, 'UPCOMING'),

    ('Leadership Conclave',
     'One-day leadership development program with guest speakers from top MNCs and startups.',
     '2026-10-02', '09:30', 'Conference Hall B', 'MBA Department', 'SEMINAR', 80, 65, 'UPCOMING'),

    ('Robotics Competition',
     'Design, build and compete with robots in obstacle courses and battle arenas.',
     '2026-10-15', '10:00', 'Engineering Block', 'Mechanical Engineering', 'COMPETITION', 120, 28, 'UPCOMING');