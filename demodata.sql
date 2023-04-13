INSERT INTO songs (songID, title, duration, releaseDate, royaltyPaid, royaltyRate) VALUES 
('s1001', 'Electric Dreamscape', '00:03:30', '2022-01-01', FALSE, 0.10),
('s1002', 'Midnight Mirage', '00:03:30', '2022-01-01', FALSE, 0.10),
('s1003', 'Echoes of You', '00:03:30', '2022-01-01', FALSE, 0.10),
('s1004', 'Rainy Nights', '00:03:30', '2022-01-01', FALSE, 0.10);

INSERT INTO songsViewed (songID, date, count) VALUES 
('s1001', '2022-01-01', 10),
('s1001', '2022-02-01', 20),
('s1001', '2022-03-01', 30),
('s1001', '2022-04-14', 500),
('s1002', '2022-01-01', 100),
('s1002', '2022-02-01', 200),
('s1002', '2022-03-01', 300),
('s1002', '2022-04-14', 1000),
('s1003', '2022-01-01', 1000),
('s1003', '2022-02-01', 2000),
('s1003', '2022-03-01', 3000),
('s1003', '2022-04-14', 100),
('s1004', '2022-01-01', 10000),
('s1004', '2022-02-01', 20000),
('s1004', '2022-03-01', 30000),
('s1004', '2022-04-14', 200);

INSERT INTO artists (artistID, name, status, type) VALUES
('ar2001', 'Forest', 'active', 'solo'),
('ar2002', 'Rain', 'active', 'solo'),
('a3', 'BTS', 'active', 'band'),
('a8', 'Bruno Mars', 'active', 'solo');


INSERT INTO monthlyListeners (artistID, date, count) VALUES 
('ar2001', '2022-01-01', 25),
('ar2001', '2022-02-01', 25),
('ar2001', '2022-03-01', 25),
('ar2001', '2022-04-01', 25),
('ar2001', '2022-01-01', 55),
('ar2002', '2022-02-01', 55),
('ar2003', '2022-03-01', 55),
('ar2004', '2022-04-01', 55);

INSERT INTO albums (albumID, name, edition, releaseYear) VALUES
('al4001', 'Electric Oasis', 'Deluxe Edition', 2020),
('al4002', 'Lost in the Echoes', 'Deluxe Edition', 2020);

INSERT INTO sungBy (artistID, songID) VALUES
('ar2001','s1001'),
('ar2001','s1002'),
('ar2002','s1003'),
('ar2002','s1004');


INSERT INTO collaboratedBy (artistID, songID) VALUES
('ar2002','s1002'),

INSERT INTO has (artistID, albumID) VALUES
('ar2001','al4001'),
('ar2002','al4001'),
('ar2002','al4002');


INSERT INTO belongsTo (albumID, songID, trackNumber) VALUES
('al4001','s1001',1),
('al4001','s1002',2),
('al4002','s1003',1),
('al4002','s1004',2);

INSERT INTO recordLabel (recordLabelID, name) VALUES
('rl3001', 'Elevate Records'),
('rl3002', 'Melodic Avenue Music');

INSERT INTO contractedWith (artistID, recordLabelID) VALUES
('ar2001','rl3001'),
('ar2001','rl3002'),

INSERT INTO podcasts (podcastID, podcastName, language, episodeCount, totalSubscribers, rating) VALUES
('p5001', 'Mind Over Matter: Exploring the Power of the Human Mind', 'English', 5, 10, 4.5);


INSERT INTO podcastHosts (podcastHostID, firstName, lastName, phone, email, city) VALUES
('ph6001','Matthew','Wilson','999999999999','mw@email.com','RALEIGH');


INSERT INTO ownedBy (podcastID, podcastHostID)
VALUES ('p5001','ph6001')

INSERT INTO podcastEpisodes (podcastEpisodeID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) VALUES
('pe7001', 'The Science of Mindfulness', '00:03:30', '2020-01-01', 100, 0),
('pe7002', 'Unlocking Your Potential', '00:03:30', '2020-01-01', 200, 0);

INSERT INTO users (userID, phone, email, registrationDate, monthlySubscriptionFee, statusOfSubscription, firstName, lastName) VALUES
('u8001', '1234567890', 'user1@example.com', '2022-01-01', 10, 'active', 'Alex', 'A'),
('u8002', '0987654321', 'user2@example.com', '2021-03-15', 10, 'active', 'John', 'J'),


INSERT INTO payments (paymentID, date)
VALUES
('1', '2022-01-01'),
('2', '2022-02-01'),
('3', '2022-03-01'),
('4', '2022-01-01'),
('5', '2022-02-01'),
('6', '2022-03-01'),
('7', '2022-01-01'),
('8', '2022-02-01');
('9', '2022-03-01');
('10', '2022-01-01'),
('11', '2022-02-01');
('12', '2022-03-01');
('13', '2022-01-01'),
('14', '2022-02-01');
('15', '2022-03-01');
('16', '2022-01-01');
('17', '2022-02-01');
('18', '2022-03-01');
('19', '2022-04-01');
('20', '2022-01-01');
('21', '2022-02-01');
('22', '2022-03-01');
('23', '2022-04-01');

INSERT INTO received (paymentID, artistID) VALUES
('1', 'ar2001'),
('2', 'ar2001'),
('3', 'ar2001'),
('4', 'ar2002'),
('5', 'ar2002'),
('6', 'ar2002');

INSERT INTO artistPayments (paymentID, paymentAmount) VALUES
('1', 4.2),
('2', 8.4),
('3', 12.6),
('4', 773.5),
('5', 1547),
('6', 2320.5);


INSERT INTO podcastHostPayments (paymentID, flatFee, bonus) VALUES 
('7', 20,0),
('8', 30,0),
('9', 40,0);


INSERT INTO podcastPayments (paymentID, podcastHostID) VALUES 
('7', 'ph6001'),
('8', 'ph6001'),
('9', 'ph6001');

INSERT INTO recordLabelPayments (paymentID, paymentAmount) VALUES
('10', 3.3),
('11', 6.6),
('12', 9.9),
('13', 330),
('14', 660),
('15', 990);

INSERT INTO paymentsReceived (paymentID, recordLabelID) VALUES
('10', 'rl3001'),
('11', 'rl3001'),
('12', 'rl3001'),
('13', 'rl3002'),
('14', 'rl3002'),
('15', 'rl3002');


INSERT INTO userPayments (paymentAmount, paymentID) VALUES
(10,'16'),
(10,'17'),
(10,'18'),
(10,'19'),
(10,'20'),
(10,'21'),
(10,'22'),
(10,'23');

INSERT INTO paymentsMade (userID, paymentID) VALUES
('u8001','16'),
('u8001','17'),
('u8001','18'),
('u8001','19'),
('u8002','20'),
('u8002','21'),
('u8002','22'),
('u8002','23');


INSERT INTO countries (countryID, name)
VALUES
('1', 'United States'),
('2', 'United Kingdom'),
('3', 'Canada'),
('4', 'Australia'),
('5', 'Japan'),
('6', 'Germany'),
('7', 'France'),
('8', 'Brazil');

INSERT INTO languages (languageID, name)
VALUES
('1', 'English'),
('2', 'Japanese'),
('3', 'French'),
('4', 'German'),
('5', 'Portuguese'),
('6', 'Spanish'),
('7', 'Italian'),
('8', 'Mandarin');

INSERT INTO genres (genreID, name)
VALUES
('1', 'Pop'),
('2', 'Rock'),
('3', 'Hip hop'),
('4', 'Electronic'),
('5', 'Classical'),
('6', 'Country'),
('7', 'Jazz'),
('8', 'Blues');

INSERT INTO sponsors (sponsorID, sponsorName)
VALUES
('1', 'Coca-Cola'),
('2', 'Pepsi'),
('3', 'McDonalds'),
('4', 'Burger King'),
('5', 'KFC'),
('6', 'Samsung'),
('7', 'Apple'),
('8', 'Microsoft');

INSERT INTO guests (guestID, name)
VALUES
('1', 'John Smith'),
('2', 'Jane Doe'),
('3', 'Bob Johnson'),
('4', 'Emma Brown'),
('5', 'Michael Lee'),
('6', 'Maria Garcia'),
('7', 'David Rodriguez'),
('8', 'Sarah Martinez');


INSERT INTO mediaStreamingService (ID, name, email)
VALUES
('1', 'Netflix', 'info@netflix.com'),
('2', 'Amazon Prime Video', 'info@amazon.com'),
('3', 'Hulu', 'info@hulu.com'),
('4', 'Disney+', 'info@disneyplus.com'),
('5', 'HBO Max', 'info@hbomax.com'),
('6', 'Apple TV+', 'info@appletvplus.com'),
('7', 'Peacock', 'info@peacock.com'),
('8', 'Paramount+', 'info@paramountplus.com');


