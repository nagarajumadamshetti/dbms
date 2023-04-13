INSERT INTO countries (countryID, name)
VALUES
(1, 'United States'),
(2, 'United Kingdom'),
(3, 'Canada'),
(4, 'Australia'),
(5, 'Japan'),
(6, 'Germany'),
(7, 'France'),
(8, 'Brazil');

INSERT INTO languages (languageID, name)
VALUES
(1, 'English'),
(2, 'Japanese'),
(3, 'French'),
(4, 'German'),
(5, 'Portuguese'),
(6, 'Spanish'),
(7, 'Italian'),
(8, 'Mandarin');

INSERT INTO genres (genreID, name)
VALUES
(1, 'Pop'),
(2, 'Rock'),
(3, 'Hip hop'),
(4, 'Electronic'),
(5, 'Classical'),
(6, 'Country'),
(7, 'Jazz'),
(8, 'Blues');

INSERT INTO sponsors (sponsorID, sponsorName)
VALUES
(1, 'Coca-Cola'),
(2, 'Pepsi'),
(3, 'McDonalds'),
(4, 'Burger King'),
(5, 'KFC'),
(6, 'Samsung'),
(7, 'Apple'),
(8, 'Microsoft');

INSERT INTO guests (guestID, name)
VALUES
(1, 'John Smith'),
(2, 'Jane Doe'),
(3, 'Bob Johnson'),
(4, 'Emma Brown'),
(5, 'Michael Lee'),
(6, 'Maria Garcia'),
(7, 'David Rodriguez'),
(8, 'Sarah Martinez');

INSERT INTO mediaStreamingService (ID, name, email)
VALUES
(1, 'Netflix', 'info@netflix.com'),
(2, 'Amazon Prime Video', 'info@amazon.com'),
(3, 'Hulu', 'info@hulu.com'),
(4, 'Disney+', 'info@disneyplus.com'),
(5, 'HBO Max', 'info@hbomax.com'),
(6, 'Apple TV+', 'info@appletvplus.com'),
(7, 'Peacock', 'info@peacock.com'),
(8, 'Paramount+', 'info@paramountplus.com');

INSERT INTO users (userID, phone, email, registrationDate, monthlySubscriptionFee, statusOfSubscription, firstName, lastName) VALUES
(1, '1234567890', 'user1@example.com', '2022-01-01', 9.99, 'active', 'John', 'Doe'),
(2, '0987654321', 'user2@example.com', '2021-03-15', 4.99, 'inactive', 'Jane', 'Doe'),
(3, '1111111111', 'user3@example.com', '2020-10-01', 14.99, 'active', 'Bob', 'Smith'),
(5, '555-555-5555', 'jane@example.com', '2023-03-10', 9.99, 'active', 'Jane', 'Doe'),
(4, '555-555-5555', 'john@example.com', '2023-03-10', 9.99, 'active', 'John', 'Doe'),
(6, '555-123-4567', 'jane.doe@example.com', '2022-01-01', 9.99, 'Active', 'Jane', 'Doe'),
(7, '555-555-5555', 'john.smith@example.com', '2021-12-01', 5.99, 'Active', 'John', 'Smith'),
(8, '555-987-6543', 'susan.jones@example.com', '2022-02-15', 7.99, 'Active', 'Susan', 'Jones'),
(9, '555-111-2222', 'jimmy.nguyen@example.com', '2022-03-01', 4.99, 'Active', 'Jimmy', 'Nguyen'),
(10, '555-555-1212', 'kim.wong@example.com', '2022-01-15', 6.99, 'Active', 'Kim', 'Wong');

-- INSERT INTO subscribeArtist (userID, artistID) VALUES
-- (1, 1),
-- (1, 2),
-- (2, 3),
-- (2, 4),
-- (3, 5),
-- (3, 6),
-- (3, 7),
-- (3, 8);

-- INSERT INTO subscribePodcast (userID, podcastID) VALUES
-- (1, 1),
-- (1, 2),
-- (2, 3),
-- (3, 4),
-- (4, 5),
-- (5, 6),
-- (6, 7),
-- (7, 8);
