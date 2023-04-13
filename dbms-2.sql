CREATE TABLE countries (
  countryID VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE languages (
  languageID VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE genres (
  genreID VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE sponsors (
  sponsorID VARCHAR(255) PRIMARY KEY NOT NULL,
  sponsorName VARCHAR(255) NOT NULL
);

CREATE TABLE guests (
  guestID VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE payments (
  paymentID VARCHAR(255) PRIMARY KEY NOT NULL,
  date DATE NOT NULL
);

CREATE TABLE mediaStreamingService (
  ID VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);

CREATE TABLE managedBy (
  paymentID VARCHAR(255) NOT NULL,
  ID VARCHAR(255) NOT NULL,
  PRIMARY KEY (paymentID, ID),
  FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (ID) REFERENCES mediaStreamingService(ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE recordLabel (
  recordLabelID VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE recordLabelPayments (
  paymentID VARCHAR(255) PRIMARY KEY NOT NULL,
  paymentAmount FLOAT NOT NULL,
  FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE paymentsReceived (
  paymentID VARCHAR(255) NOT NULL,
  recordLabelID VARCHAR(255) NOT NULL,
  PRIMARY KEY (paymentID, recordLabelID),
  FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (recordLabelID) REFERENCES recordLabel(recordLabelID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE artists (
  artistID VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL,
  status VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL
); 

CREATE TABLE primaryGenre (
  artistID VARCHAR(255) PRIMARY KEY NOT NULL,
  genreID VARCHAR(255) NOT NULL,
  FOREIGN KEY (artistID) REFERENCES artists(artistID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (genreID) REFERENCES genres(genreID) ON UPDATE CASCADE ON DELETE CASCADE
); 

CREATE TABLE artistPayments (
  paymentID VARCHAR(255) PRIMARY KEY NOT NULL,
  paymentAmount FLOAT NOT NULL,
  FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE received (
  paymentID VARCHAR(255) NOT NULL,
  artistID VARCHAR(255) NOT NULL,
  PRIMARY KEY (paymentID, artistID),
  FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (artistID) REFERENCES artists(artistID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE contractedWith (
  artistID VARCHAR(255) NOT NULL,
  recordLabelID VARCHAR(255) NOT NULL,
  PRIMARY KEY (recordLabelID, artistID),
  FOREIGN KEY (recordLabelID) REFERENCES recordLabel(recordLabelID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (artistID) REFERENCES artists(artistID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE basedIn (
  artistID VARCHAR(255) NOT NULL,
  countryID VARCHAR(255) NOT NULL,
  PRIMARY KEY (artistID, countryID),
  FOREIGN KEY (countryID) REFERENCES countries(countryID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (artistID) REFERENCES artists(artistID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE monthlyListeners (
  artistID VARCHAR(255) NOT NULL,
  date DATE NOT NULL,
  count INT NOT NULL,
  PRIMARY KEY (artistID, date),
  FOREIGN KEY (artistID) REFERENCES artists(artistID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE songs (
  songID VARCHAR(255) NOT NULL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  duration TIME NOT NULL,
  releaseDate DATE NOT NULL,
  royaltyPaid FLOAT NOT NULL,
  royaltyRate FLOAT NOT NULL
); 
CREATE TABLE releasedIn (
  songID VARCHAR(255) NOT NULL,
  countryID VARCHAR(255) NOT NULL,
  PRIMARY KEY (songID, countryID),
  FOREIGN KEY (countryID) REFERENCES countries(countryID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (songID) REFERENCES songs(songID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE songsViewed (
  songID VARCHAR(255) NOT NULL,
  date DATE NOT NULL,
  count INT NOT NULL,
  PRIMARY KEY (songID, date),
  FOREIGN KEY (songID) REFERENCES songs(songID) ON UPDATE CASCADE ON DELETE CASCADE
); 
CREATE TABLE sungIn (
  songID VARCHAR(255) NOT NULL PRIMARY KEY,
  languageID VARCHAR(255) NOT NULL,
  FOREIGN KEY (songID) REFERENCES songs(songID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (languageID) REFERENCES languages(languageID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE generedIn (
  songID VARCHAR(255) NOT NULL,
  genreID VARCHAR(255) NOT NULL,
  PRIMARY KEY (songID,genreID),
  FOREIGN KEY (songID) REFERENCES songs(songID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (genreID) REFERENCES genres(genreID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE sungBy (
  artistID VARCHAR(255) NOT NULL,
  songID VARCHAR(255) NOT NULL PRIMARY KEY,
  FOREIGN KEY (songID) REFERENCES songs(songID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (artistID) REFERENCES artists(artistID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE collaboratedBy (
  artistID VARCHAR(255) NOT NULL,
  songID VARCHAR(255) NOT NULL,
  PRIMARY KEY (artistID, songID),
  FOREIGN KEY (songID) REFERENCES songs(songID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (artistID) REFERENCES artists(artistID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE albums (
  albumID VARCHAR(255) NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  edition VARCHAR(255) NOT NULL,
  releaseYear INT NOT NULL
);

CREATE TABLE has (
  artistID VARCHAR(255) NOT NULL,
  albumID VARCHAR(255) NOT NULL,
  PRIMARY KEY (artistID, albumID),
  FOREIGN KEY (albumID) REFERENCES albums(albumID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (artistID) REFERENCES artists(artistID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE belongsTo (
  albumID VARCHAR(255) NOT NULL,
  songID VARCHAR(255) NOT NULL,
  trackNumber INT NOT NULL,
  PRIMARY KEY (songID, albumID),
  FOREIGN KEY (albumID) REFERENCES albums(albumID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (songID) REFERENCES songs(songID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE users (
  userID VARCHAR(255) NOT NULL PRIMARY KEY,
  phone VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  registrationDate DATE NOT NULL,
  monthlySubscriptionFee FLOAT NOT NULL,
  statusOfSubscription VARCHAR(255) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL
);

CREATE TABLE subscribeArtist (
  userID VARCHAR(255) NOT NULL,
  artistID VARCHAR(255) NOT NULL,
  PRIMARY KEY (userID, artistID),
  FOREIGN KEY (userID) REFERENCES users(userID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (artistID) REFERENCES artists(artistID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE userPayments (
  paymentID VARCHAR(255) NOT NULL PRIMARY KEY,
  paymentAmount INT NOT NULL,
  FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE paymentsMade (
  userID VARCHAR(255) NOT NULL,
  paymentID VARCHAR(255) NOT NULL,
  PRIMARY KEY (userID, paymentID),
  FOREIGN KEY (userID) REFERENCES users(userID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE podcasts (
  podcastID VARCHAR(255) NOT NULL PRIMARY KEY,
  podcastName VARCHAR(255) NOT NULL,
  language VARCHAR(255) NOT NULL,
  episodeCount INT NOT NULL,
  totalSubscribers INT NOT NULL,
  rating INT NOT NULL
); 

CREATE TABLE sponsoredBy (
  podcastID VARCHAR(255) NOT NULL,
  sponsorID VARCHAR(255) NOT NULL,
  PRIMARY KEY (podcastID, sponsorID),
  FOREIGN KEY (podcastID) REFERENCES podcasts(podcastID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (sponsorID) REFERENCES sponsors(sponsorID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE subscribePodcast (
  userID VARCHAR(255) NOT NULL,
  podcastID VARCHAR(255) NOT NULL,
  PRIMARY KEY (userID, podcastID),
  FOREIGN KEY (userID) REFERENCES users(userID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (podcastID) REFERENCES podcasts(podcastID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE podcastGeneredIn (
  podcastID VARCHAR(255) NOT NULL,
  genreID VARCHAR(255) NOT NULL,
  PRIMARY KEY (podcastID, genreID),
  FOREIGN KEY (genreID) REFERENCES genres(genreID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (podcastID) REFERENCES podcasts(podcastID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE originCountry (
  podcastID VARCHAR(255) NOT NULL PRIMARY KEY,
  countryID VARCHAR(255) NOT NULL,
  FOREIGN KEY (countryID) REFERENCES countries(countryID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (podcastID) REFERENCES podcasts(podcastID) ON UPDATE CASCADE ON DELETE CASCADE
); 

CREATE TABLE podcastEpisodes (
  podcastEpisodeID VARCHAR(255) NOT NULL PRIMARY KEY,
  episodeTitle VARCHAR(255) NOT NULL,
  duration TIME NOT NULL,
  releaseDate DATE NOT NULL,
  listeningCount INT NOT NULL,
  advertisementCount INT NOT NULL
);

CREATE TABLE partOf (
  podcastID VARCHAR(255) NOT NULL,
  podcastEpisodeID VARCHAR(255) NOT NULL,
  PRIMARY KEY (podcastID, podcastEpisodeID),
  FOREIGN KEY (podcastID) REFERENCES podcasts(podcastID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (podcastEpisodeID) REFERENCES podcastEpisodes(podcastEpisodeID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE guestsFeatured (
  guestID VARCHAR(255) NOT NULL,
  podcastEpisodeID VARCHAR(255) NOT NULL,
  PRIMARY KEY (guestID, podcastEpisodeID),
  FOREIGN KEY (guestID) REFERENCES guests(guestID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (podcastEpisodeID) REFERENCES podcastEpisodes(podcastEpisodeID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE podcastHosts (
  podcastHostID VARCHAR(255) PRIMARY KEY NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL
); 

CREATE TABLE ownedBy (
  podcastID VARCHAR(255) NOT NULL,
  podcastHostID VARCHAR(255) NOT NULL,
  PRIMARY KEY (podcastID, podcastHostID),
  FOREIGN KEY (podcastID) REFERENCES podcasts(podcastID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (podcastHostID) REFERENCES podcastHosts(podcastHostID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE podcastHostPayments (
  paymentID VARCHAR(255) PRIMARY KEY NOT NULL,
  flatFee FLOAT NOT NULL,
  bonus FLOAT NOT NULL,
  FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON UPDATE CASCADE ON DELETE CASCADE
);

-- episodeID VARCHAR(255) NOT NULL,
CREATE TABLE podcastPayments (
paymentID VARCHAR(255) NOT NULL,
podcastHostID VARCHAR(255) NOT NULL,
PRIMARY KEY (paymentID, podcastHostID),
FOREIGN KEY (podcastHostID) REFERENCES podcastHosts(podcastHostID) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON UPDATE CASCADE ON DELETE CASCADE
);

-------------------------------------------------------------------------------------------------------------
---------------------------------------------INSERT DATA-----------------------------------------------------
-------------------------------------------------------------------------------------------------------------
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

INSERT INTO payments (paymentID, date)
VALUES
(1, '2022-01-01'),
(2, '2022-02-01'),
(3, '2022-03-01'),
(4, '2022-04-01'),
(5, '2022-05-01'),
(6, '2022-06-01'),
(7, '2022-07-01'),
(8, '2022-08-01');

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

INSERT INTO managedBy (paymentID, ID) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 3),
(5, 4),
(6, 4),
(7, 5),
(8, 6);

INSERT INTO recordLabel (recordLabelID, name) VALUES
(1, 'Universal Music Group'),
(2, 'Sony Music Entertainment'),
(3, 'Warner Music Group'),
(4, 'EMI'),
(5, 'Atlantic Records'),
(6, 'Capitol Records'),
(7, 'Republic Records'),
(8, 'Island Records');

INSERT INTO recordLabelPayments (paymentID, paymentAmount) VALUES
(1, 10000.00),
(2, 5000.00),
(3, 7000.00),
(4, 3000.00),
(5, 8000.00),
(6, 9000.00),
(7, 2000.00),
(8, 1000.00);

INSERT INTO paymentsReceived (paymentID, recordLabelID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2),
(6, 3),
(7, 4),
(8, 5);

-- INSERT INTO artists (artistID, name, status, type, countryID, monthlyListeners) VALUES
-- (1, 'Taylor Swift', 'active', 'solo', 1, 7),
-- (2, 'The Beatles', 'inactive', 'band', 2, 8),
-- (3, 'BTS', 'active', 'band', 3, 9),
-- (4, 'Ariana Grande', 'active', 'solo', 4, 10),
-- (5, 'Elton John', 'inactive', 'solo', 5, 1),
-- (6, 'Ed Sheeran', 'active', 'solo', 6, 2),
-- (7, 'Coldplay', 'active', 'band', 7, 3),
-- (8, 'Bruno Mars', 'active', 'solo', 8, 4);

INSERT INTO artists (artistID, name, status, type) VALUES
(1, 'Taylor Swift', 'active', 'solo'),
(2, 'The Beatles', 'inactive', 'band'),
(3, 'BTS', 'active', 'band'),
(4, 'Ariana Grande', 'active', 'solo'),
(5, 'Elton John', 'inactive', 'solo'),
(6, 'Ed Sheeran', 'active', 'solo'),
(7, 'Coldplay', 'active', 'band'),
(8, 'Bruno Mars', 'active', 'solo');

INSERT INTO primaryGenre (artistID, genreID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

INSERT INTO artistPayments (paymentID, paymentAmount) VALUES
(1, 12000.00),
(2, 8000.00),
(3, 10000.00),
(4, 5000.00),
(5, 6000.00),
(6, 7000.00),
(7, 2000.00),
(8, 1500.00);

INSERT INTO received (paymentID, artistID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

INSERT INTO contractedWith (artistID, recordLabelID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

INSERT INTO basedIn (artistID, countryID) VALUES 
(1, 2), 
(2, 5), 
(3, 1), 
(4, 4), 
(5, 6), 
(6, 3), 
(7, 2), 
(8, 5);

INSERT INTO monthlyListeners (artistID, date, count) VALUES 
(1, '2022-01-01', 10000),
(2, '2022-01-01', 20000),
(3, '2022-01-01', 30000),
(4, '2022-01-01', 40000),
(5, '2022-01-01', 50000),
(6, '2022-01-01', 60000),
(7, '2022-01-01', 70000),
(8, '2022-01-01', 80000);

INSERT INTO songs (songID, title, duration, releaseDate, royaltyPaid, royaltyRate) VALUES 
(1, 'Song A', '00:03:30', '2022-01-01', 1000.00, 0.10),
(2, 'Song B', '00:04:15', '2022-01-02', 1500.00, 0.12),
(3, 'Song C', '00:03:45', '2022-01-03', 2000.00, 0.15),
(4, 'Song D', '00:03:00', '2022-01-04', 2500.00, 0.18),
(5, 'Song E', '00:05:00', '2022-01-05', 3000.00, 0.20),
(6, 'Song F', '00:03:20', '2022-01-06', 3500.00, 0.22),
(7, 'Song G', '00:04:45', '2022-01-07', 4000.00, 0.25),
(8, 'Song H', '00:02:45', '2022-01-08', 4500.00, 0.30);

-- INSERT INTO songs (songID, title, duration, releaseDate, royaltyPaid, royaltyRate, playCount) VALUES 
-- (1, 'Song A', '00:03:30', '2022-01-01', 1000.00, 0.10, 11),
-- (2, 'Song B', '00:04:15', '2022-01-02', 1500.00, 0.12, 12),
-- (3, 'Song C', '00:03:45', '2022-01-03', 2000.00, 0.15, 13),
-- (4, 'Song D', '00:03:00', '2022-01-04', 2500.00, 0.18, 14),
-- (5, 'Song E', '00:05:00', '2022-01-05', 3000.00, 0.20, 15),
-- (6, 'Song F', '00:03:20', '2022-01-06', 3500.00, 0.22, 16),
-- (7, 'Song G', '00:04:45', '2022-01-07', 4000.00, 0.25, 17),
-- (8, 'Song H', '00:02:45', '2022-01-08', 4500.00, 0.30, 18);

INSERT INTO releasedIn (songID, countryID) VALUES 
(1, 2), 
(2, 5), 
(3, 1), 
(4, 4), 
(5, 6), 
(6, 3), 
(7, 2), 
(8, 5);

INSERT INTO songsViewed (songID, date, count) VALUES 
(1, '2022-01-01', 100000),
(2, '2022-01-01', 200000),
(3, '2022-01-01', 300000),
(4, '2022-01-01', 400000),
(5, '2022-01-01', 500000),
(6, '2022-01-01', 600000),
(7, '2022-01-01', 700000),
(8, '2022-01-01', 800000);

INSERT INTO sungIn (songID, languageID) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(5, 2),
(6, 4),
(7, 3),
(8, 2);

INSERT INTO generedIn (songID, genreID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 1),
(6, 3),
(7, 2),
(8, 4);

INSERT INTO sungBy (artistID, songID) VALUES
(1, 1),
(2, 2),
(3, 3),
(1, 4),
(2, 5),
(3, 6),
(1, 7),
(2, 8);

INSERT INTO collaboratedBy (artistID, songID) VALUES
(1, 1),
(2, 2),
(3, 2),
(2, 3),
(1, 4),
(3, 5),
(1, 6),
(2, 6);

INSERT INTO albums (albumID, name, edition, releaseYear) VALUES
(1, 'Album 1', 'Deluxe Edition', 2020),
(2, 'Album 2', 'Standard Edition', 2018),
(3, 'Album 3', 'Deluxe Edition', 2021),
(4, 'Album 4', 'Standard Edition', 2019),
(5, 'Album 5', 'Deluxe Edition', 2022),
(6, 'Album 6', 'Standard Edition', 2017),
(7, 'Album 7', 'Deluxe Edition', 2023),
(8, 'Album 8', 'Standard Edition', 2020);

INSERT INTO has (artistID, albumID) VALUES
(1, 1),
(2, 2),
(3, 3),
(1, 4),
(2, 5),
(3, 6),
(1, 7),
(2, 8);

INSERT INTO belongsTo (albumID, songID, trackNumber) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 3),
(2, 4, 1),
(2, 5, 2),
(2, 6, 3),
(3, 7, 1),
(3, 8, 2);

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

INSERT INTO subscribeArtist (userID, artistID) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8);

INSERT INTO userPayments (paymentAmount, paymentID) VALUES
(1000, 1),
(1000, 2),
(2000, 3),
(3222, 4),
(4000, 5),
(599, 6),
(69, 7),
(77, 8);

INSERT INTO paymentsMade (userID, paymentID) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8);

INSERT INTO podcasts (podcastID, podcastName, language, episodeCount, totalSubscribers, rating) VALUES
(1, 'The Joe Rogan Experience', 'English', 1500, 5000000, 1),
(2, 'Serial', 'English', 50, 2000000, 5),
(3, 'Crime Junkie', 'English', 200, 1000000, 4),
(4, 'Radiolab', 'English', 400, 500000, 3),
(5, 'Pod Save America', 'English', 300, 750000, 4),
(6, 'Stuff You Should Know', 'English', 800, 1000000, 5),
(7, 'My Favorite Murder', 'English', 400, 500000, 3),
(8, 'The Daily', 'English', 1000, 3000000, 1);

INSERT INTO sponsoredBy (podcastID, sponsorID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

INSERT INTO subscribePodcast (userID, podcastID) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8);

INSERT INTO podcastGeneredIn (podcastID, genreID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

INSERT INTO originCountry (podcastID, countryID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

INSERT INTO podcastEpisodes (podcastEpisodeID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) VALUES
(1, 'Episode 1: Joe Rogan and Elon Musk', '00:03:30', '2020-01-01', 500000, 100),
(2, 'Episode 1: The Case Against Adnan Syed', '00:03:31', '2019-01-01', 200000, 50),
(3, 'Episode 1: MURDERED: Angela Mischelle Lawless', '00:03:32', '2021-01-01', 100000, 20),
(4, 'Episode 1: CRISPR', '00:03:33', '2020-02-01', 50000, 10),
(5, 'Episode 1: Obama on "Hope"', '00:03:34', '2019-10-01', 75000, 15),
(6, 'Episode 1: SYSK Selects: How Tsunamis Work', '00:03:35', '2020-06-01', 50000, 10),
(7, 'Episode 1: MFM Minisode 1', '00:03:36', '2018-01-01', 500,22),
(8, 'Episode 9', '00:03:37', '2023-03-05', 1000, 10);

-- Insert 8 records into partOf table
INSERT INTO partOf (podcastID, podcastEpisodeID)
VALUES (1, 1), (1, 2), (1, 3), (2, 4), (2, 5), (2, 6), (3, 7), (3, 8);

-- Insert 8 records into guestsFeatured table
INSERT INTO guestsFeatured (guestID, podcastEpisodeID)
VALUES (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8);

-- Insert 8 records into podcastHosts table
INSERT INTO podcastHosts (podcastHostID, firstName, lastName, phone, email, city)
VALUES (1, 'John', 'Doe', '123-456-7890', 'john.doe@example.com', 'New York'),
(2, 'Jane', 'Doe', '234-567-8901', 'jane.doe@example.com', 'Los Angeles'),
(3, 'Bob', 'Smith', '345-678-9012', 'bob.smith@example.com', 'Chicago'),
(4, 'Alice', 'Johnson', '456-789-0123', 'alice.johnson@example.com', 'Houston'),
(5, 'Tom', 'Williams', '567-890-1234', 'tom.williams@example.com', 'Philadelphia'),
(6, 'Emily', 'Brown', '678-901-2345', 'emily.brown@example.com', 'Phoenix'),
(7, 'David', 'Wilson', '789-012-3456', 'david.wilson@example.com', 'San Diego'),
(8, 'Amy', 'Miller', '890-123-4567', 'amy.miller@example.com', 'Dallas');

-- Insert 8 records into ownedBy table
INSERT INTO ownedBy (podcastID, podcastHostID)
VALUES (1, 1), (2, 2), (3, 3), (1, 4), (2, 5), (3, 6), (1, 7), (2, 8);

-- Insert 8 records into podcastHostPayments table
INSERT INTO podcastHostPayments (paymentID, flatFee, bonus)
VALUES (1, 1000.00, 500.00), (2, 2000.00, 750.00), (3, 1500.00, 800.00), (4, 1200.00, 600.00),
(5, 2500.00, 1000.00), (6, 1800.00, 900.00), (7, 3000.00, 1200.00), (8, 2200.00, 1000.00);

-- Insert 8 records into podcastPayments table
INSERT INTO podcastPayments (paymentID, podcastHostID)
VALUES (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8);

-- ---------------------------------------------------------------------------------------------------------
-- --------------------------------------------- show table contents----------------------------------------
-- ---------------------------------------------------------------------------------------------------------

-- SELECT * FROM albums;
-- SELECT * FROM artistpayments;
-- SELECT * FROM artists;
-- SELECT * FROM basedin;
-- SELECT * FROM belongsto;
-- SELECT * FROM collaboratedby;
-- SELECT * FROM contractedwith;
-- SELECT * FROM countries;
-- SELECT * FROM generedin;
-- SELECT * FROM genres;
-- SELECT * FROM guests;
-- SELECT * FROM guestsfeatured;
-- SELECT * FROM has;
-- SELECT * FROM languages;
-- SELECT * FROM managedby;
-- SELECT * FROM mediastreamingservice;
-- SELECT * FROM monthlylisteners;
-- SELECT * FROM origincountry;
-- SELECT * FROM ownedby;
-- SELECT * FROM partof;
-- SELECT * FROM payments;
-- SELECT * FROM paymentsmade;
-- SELECT * FROM paymentsreceived;
-- SELECT * FROM podcastepisodes;
-- SELECT * FROM podcastgeneredin;
-- SELECT * FROM podcasthostpayments;
-- SELECT * FROM podcasthosts;
-- SELECT * FROM podcastPayments;
-- SELECT * FROM podcasts;
-- SELECT * FROM primarygenre;
-- SELECT * FROM received;
-- SELECT * FROM recordlabel;
-- SELECT * FROM recordlabelpayments;
-- SELECT * FROM releasedin;
-- SELECT * FROM songs;
-- SELECT * FROM songsviewed;
-- SELECT * FROM sponsoredby;
-- SELECT * FROM sponsors;
-- SELECT * FROM subscribeartist;
-- SELECT * FROM subscribepodcast;
-- SELECT * FROM sungby;
-- SELECT * FROM sungin;
-- SELECT * FROM users;


-- MariaDB [nagaraj]> show tables;
-- +------------------------------+
-- | Tables_in_nagaraj            |
-- +------------------------------+
-- | albums                       |
-- | artistpayments               |
-- | artists                      |
-- | basedin                      |
-- | belongsto                    |
-- | collaboratedby               |
-- | contractedwith               |
-- | countries                    |
-- | generedin                    |
-- | genres                       |
-- | guests                       |
-- | guestsfeatured               |
-- | has                          |
-- | languages                    |
-- | managedby                    |
-- | mediastreamingservice        |
-- | monthlylisteners             |
-- | origincountry                |
-- | ownedby                      |
-- | partof                       |
-- | payments                     |
-- | paymentsmade                 |
-- | paymentsreceived             |
-- | podcastepisodes              |
-- | podcastgeneredin             |
-- | podcasthostpayments          |
-- | podcasthosts                 |
-- | podcastpaymentsrelationtable |
-- | podcasts                     |
-- | primarygenre                 |
-- | received                     |
-- | recordlabel                  |
-- | recordlabelpayments          |
-- | releasedin                   |
-- | songs                        |
-- | songsviewed                  |
-- | sponsoredby                  |
-- | sponsors                     |
-- | subscribeartist              |
-- | subscribepodcast             |
-- | sungby                       |
-- | sungin                       |
-- | users                        |
-- +------------------------------+
-- 43 rows in set (0.001 sec)

-- MariaDB [nagaraj]> SELECT * FROM albums;
-- +---------+---------+------------------+-------------+
-- | albumID | name    | edition          | releaseYear |
-- +---------+---------+------------------+-------------+
-- |       1 | Album 1 | Deluxe Edition   |        2020 |
-- |       2 | Album 2 | Standard Edition |        2018 |
-- |       3 | Album 3 | Deluxe Edition   |        2021 |
-- |       4 | Album 4 | Standard Edition |        2019 |
-- |       5 | Album 5 | Deluxe Edition   |        2022 |
-- |       6 | Album 6 | Standard Edition |        2017 |
-- |       7 | Album 7 | Deluxe Edition   |        2023 |
-- |       8 | Album 8 | Standard Edition |        2020 |
-- +---------+---------+------------------+-------------+
-- 8 rows in set (0.001 sec)

-- MariaDB [nagaraj]> SELECT * FROM artistpayments;
-- +-----------+---------------+
-- | paymentID | paymentAmount |
-- +-----------+---------------+
-- |         1 |         12000 |
-- |         2 |          8000 |
-- |         3 |         10000 |
-- |         4 |          5000 |
-- |         5 |          6000 |
-- |         6 |          7000 |
-- |         7 |          2000 |
-- |         8 |          1500 |
-- +-----------+---------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM artists;
-- +----------+---------------+----------+------+-----------+
-- | artistID | name          | status   | type | countryID |
-- +----------+---------------+----------+------+-----------+
-- |        1 | Taylor Swift  | active   | solo |         1 |
-- |        2 | The Beatles   | inactive | band |         2 |
-- |        3 | BTS           | active   | band |         3 |
-- |        4 | Ariana Grande | active   | solo |         4 |
-- |        5 | Elton John    | inactive | solo |         5 |
-- |        6 | Ed Sheeran    | active   | solo |         6 |
-- |        7 | Coldplay      | active   | band |         7 |
-- |        8 | Bruno Mars    | active   | solo |         8 |
-- +----------+---------------+----------+------+-----------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM basedin;
-- +----------+-----------+
-- | artistID | countryID |
-- +----------+-----------+
-- |        1 |         2 |
-- |        2 |         5 |
-- |        3 |         1 |
-- |        4 |         4 |
-- |        5 |         6 |
-- |        6 |         3 |
-- |        7 |         2 |
-- |        8 |         5 |
-- +----------+-----------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM belongsto;
-- +---------+--------+-------------+
-- | albumID | songID | trackNumber |
-- +---------+--------+-------------+
-- |       1 |      1 |           1 |
-- |       1 |      2 |           2 |
-- |       1 |      3 |           3 |
-- |       2 |      4 |           1 |
-- |       2 |      5 |           2 |
-- |       2 |      6 |           3 |
-- |       3 |      7 |           1 |
-- |       3 |      8 |           2 |
-- +---------+--------+-------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM collaboratedby;
-- +----------+--------+
-- | artistID | songID |
-- +----------+--------+
-- |        1 |      1 |
-- |        1 |      4 |
-- |        1 |      6 |
-- |        2 |      2 |
-- |        2 |      3 |
-- |        2 |      6 |
-- |        3 |      2 |
-- |        3 |      5 |
-- +----------+--------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM contractedwith;
-- +----------+---------------+
-- | artistID | recordLabelID |
-- +----------+---------------+
-- |        1 |             1 |
-- |        2 |             2 |
-- |        3 |             3 |
-- |        4 |             4 |
-- |        5 |             5 |
-- |        6 |             6 |
-- |        7 |             7 |
-- |        8 |             8 |
-- +----------+---------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM countries;
-- +-----------+----------------+
-- | countryID | name           |
-- +-----------+----------------+
-- |         1 | United States  |
-- |         2 | United Kingdom |
-- |         3 | Canada         |
-- |         4 | Australia      |
-- |         5 | Japan          |
-- |         6 | Germany        |
-- |         7 | France         |
-- |         8 | Brazil         |
-- +-----------+----------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM generedin;
-- +--------+---------+
-- | songID | genreID |
-- +--------+---------+
-- |      1 |       1 |
-- |      2 |       2 |
-- |      3 |       3 |
-- |      4 |       4 |
-- |      5 |       1 |
-- |      6 |       3 |
-- |      7 |       2 |
-- |      8 |       4 |
-- +--------+---------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM genres;
-- +---------+------------+
-- | genreID | name       |
-- +---------+------------+
-- |       1 | Pop        |
-- |       2 | Rock       |
-- |       3 | Hip hop    |
-- |       4 | Electronic |
-- |       5 | Classical  |
-- |       6 | Country    |
-- |       7 | Jazz       |
-- |       8 | Blues      |
-- +---------+------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM guests;
-- +---------+-----------------+
-- | guestID | name            |
-- +---------+-----------------+
-- |       1 | John Smith      |
-- |       2 | Jane Doe        |
-- |       3 | Bob Johnson     |
-- |       4 | Emma Brown      |
-- |       5 | Michael Lee     |
-- |       6 | Maria Garcia    |
-- |       7 | David Rodriguez |
-- |       8 | Sarah Martinez  |
-- +---------+-----------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM guestsfeatured;
-- +---------+------------------+
-- | guestID | podcastEpisodeID |
-- +---------+------------------+
-- |       1 |                1 |
-- |       2 |                2 |
-- |       3 |                3 |
-- |       4 |                4 |
-- |       5 |                5 |
-- |       6 |                6 |
-- |       7 |                7 |
-- |       8 |                8 |
-- +---------+------------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM has;
-- +----------+---------+
-- | artistID | albumID |
-- +----------+---------+
-- |        1 |       1 |
-- |        1 |       4 |
-- |        1 |       7 |
-- |        2 |       2 |
-- |        2 |       5 |
-- |        2 |       8 |
-- |        3 |       3 |
-- |        3 |       6 |
-- +----------+---------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM languages;
-- +------------+------------+
-- | languageID | name       |
-- +------------+------------+
-- |          1 | English    |
-- |          2 | Japanese   |
-- |          3 | French     |
-- |          4 | German     |
-- |          5 | Portuguese |
-- |          6 | Spanish    |
-- |          7 | Italian    |
-- |          8 | Mandarin   |
-- +------------+------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM managedby;
-- +-----------+----+
-- | paymentID | ID |
-- +-----------+----+
-- |         1 |  1 |
-- |         2 |  1 |
-- |         3 |  2 |
-- |         4 |  3 |
-- |         5 |  4 |
-- |         6 |  4 |
-- |         7 |  5 |
-- |         8 |  6 |
-- +-----------+----+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM mediastreamingservice;
-- +----+--------------------+------------------------+
-- | ID | name               | email                  |
-- +----+--------------------+------------------------+
-- |  1 | Netflix            | info@netflix.com       |
-- |  2 | Amazon Prime Video | info@amazon.com        |
-- |  3 | Hulu               | info@hulu.com          |
-- |  4 | Disney+            | info@disneyplus.com    |
-- |  5 | HBO Max            | info@hbomax.com        |
-- |  6 | Apple TV+          | info@appletvplus.com   |
-- |  7 | Peacock            | info@peacock.com       |
-- |  8 | Paramount+         | info@paramountplus.com |
-- +----+--------------------+------------------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM monthlylisteners;
-- +----------+------------+-------+
-- | artistID | date       | count |
-- +----------+------------+-------+
-- |        1 | 2022-01-01 | 10000 |
-- |        2 | 2022-01-01 | 20000 |
-- |        3 | 2022-01-01 | 30000 |
-- |        4 | 2022-01-01 | 40000 |
-- |        5 | 2022-01-01 | 50000 |
-- |        6 | 2022-01-01 | 60000 |
-- |        7 | 2022-01-01 | 70000 |
-- |        8 | 2022-01-01 | 80000 |
-- +----------+------------+-------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM origincountry;
-- +-----------+-----------+
-- | podcastID | countryID |
-- +-----------+-----------+
-- |         1 |         1 |
-- |         2 |         2 |
-- |         3 |         3 |
-- |         4 |         4 |
-- |         5 |         5 |
-- |         6 |         6 |
-- |         7 |         7 |
-- |         8 |         8 |
-- +-----------+-----------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM ownedby;
-- +-----------+---------------+
-- | podcastID | podcastHostID |
-- +-----------+---------------+
-- |         1 |             1 |
-- |         1 |             4 |
-- |         1 |             7 |
-- |         2 |             2 |
-- |         2 |             5 |
-- |         2 |             8 |
-- |         3 |             3 |
-- |         3 |             6 |
-- +-----------+---------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM partof;
-- +-----------+------------------+
-- | podcastID | podcastEpisodeID |
-- +-----------+------------------+
-- |         1 |                1 |
-- |         1 |                2 |
-- |         1 |                3 |
-- |         2 |                4 |
-- |         2 |                5 |
-- |         2 |                6 |
-- |         3 |                7 |
-- |         3 |                8 |
-- +-----------+------------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM payments;
-- +-----------+------------+
-- | paymentID | date       |
-- +-----------+------------+
-- |         1 | 2022-01-01 |
-- |         2 | 2022-02-01 |
-- |         3 | 2022-03-01 |
-- |         4 | 2022-04-01 |
-- |         5 | 2022-05-01 |
-- |         6 | 2022-06-01 |
-- |         7 | 2022-07-01 |
-- |         8 | 2022-08-01 |
-- +-----------+------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM paymentsmade;
-- +--------+-----------+
-- | userID | paymentID |
-- +--------+-----------+
-- |      1 |         1 |
-- |      1 |         2 |
-- |      2 |         3 |
-- |      3 |         4 |
-- |      4 |         5 |
-- |      5 |         6 |
-- |      6 |         7 |
-- |      7 |         8 |
-- +--------+-----------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM paymentsreceived;
-- +-----------+---------------+
-- | paymentID | recordLabelID |
-- +-----------+---------------+
-- |         1 |             1 |
-- |         2 |             2 |
-- |         3 |             3 |
-- |         4 |             1 |
-- |         5 |             2 |
-- |         6 |             3 |
-- |         7 |             4 |
-- |         8 |             5 |
-- +-----------+---------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM podcastepisodes;
-- +------------------+-----------------------------------------------+----------+-------------+----------------+--------------------+
-- | podcastEpisodeID | episodeTitle                                  | duration | releaseDate | listeningCount | advertisementCount |
-- +------------------+-----------------------------------------------+----------+-------------+----------------+--------------------+
-- |                1 | Episode 1: Joe Rogan and Elon Musk            |    120.5 | 2020-01-01  |         500000 |                100 |
-- |                2 | Episode 1: The Case Against Adnan Syed        |       90 | 2019-01-01  |         200000 |                 50 |
-- |                3 | Episode 1: MURDERED: Angela Mischelle Lawless |       60 | 2021-01-01  |         100000 |                 20 |
-- |                4 | Episode 1: CRISPR                             |     45.5 | 2020-02-01  |          50000 |                 10 |
-- |                5 | Episode 1: Obama on "Hope"                    |     75.5 | 2019-10-01  |          75000 |                 15 |
-- |                6 | Episode 1: SYSK Selects: How Tsunamis Work    |       60 | 2020-06-01  |          50000 |                 10 |
-- |                7 | Episode 1: MFM Minisode 1                     |       30 | 2018-01-01  |
--  500 |                 22 |
-- |                8 | Episode 9                                     |     45.5 | 2023-03-05  |
-- 1000 |                 10 |
-- +------------------+-----------------------------------------------+----------+-------------+----------------+--------------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM podcastgeneredin;
-- +-----------+---------+
-- | podcastID | genreID |
-- +-----------+---------+
-- |         1 |       1 |
-- |         2 |       2 |
-- |         3 |       3 |
-- |         4 |       4 |
-- |         5 |       5 |
-- |         6 |       6 |
-- |         7 |       7 |
-- |         8 |       8 |
-- +-----------+---------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM podcasthostpayments;
-- +-----------+---------+-------+
-- | paymentID | flatFee | bonus |
-- +-----------+---------+-------+
-- |         1 |    1000 |   500 |
-- |         2 |    2000 |   750 |
-- |         3 |    1500 |   800 |
-- |         4 |    1200 |   600 |
-- |         5 |    2500 |  1000 |
-- |         6 |    1800 |   900 |
-- |         7 |    3000 |  1200 |
-- |         8 |    2200 |  1000 |
-- +-----------+---------+-------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM podcasthosts;
-- +---------------+-----------+----------+--------------+---------------------------+--------------+
-- | podcastHostID | firstName | lastName | phone        | email                     | city         |
-- +---------------+-----------+----------+--------------+---------------------------+--------------+
-- |             1 | John      | Doe      | 123-456-7890 | john.doe@example.com      | New York     |
-- |             2 | Jane      | Doe      | 234-567-8901 | jane.doe@example.com      | Los Angeles  |
-- |             3 | Bob       | Smith    | 345-678-9012 | bob.smith@example.com     | Chicago      |
-- |             4 | Alice     | Johnson  | 456-789-0123 | alice.johnson@example.com | Houston      |
-- |             5 | Tom       | Williams | 567-890-1234 | tom.williams@example.com  | Philadelphia |
-- |             6 | Emily     | Brown    | 678-901-2345 | emily.brown@example.com   | Phoenix      |
-- |             7 | David     | Wilson   | 789-012-3456 | david.wilson@example.com  | San Diego    |
-- |             8 | Amy       | Miller   | 890-123-4567 | amy.miller@example.com    | Dallas       |
-- +---------------+-----------+----------+--------------+---------------------------+--------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM podcastpaymentsrelationtable;
-- +-----------+---------------+
-- | paymentID | podcastHostID |
-- +-----------+---------------+
-- |         1 |             1 |
-- |         2 |             2 |
-- |         3 |             3 |
-- |         4 |             4 |
-- |         5 |             5 |
-- |         6 |             6 |
-- |         7 |             7 |
-- |         8 |             8 |
-- +-----------+---------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM podcasts;
-- +-----------+--------------------------+----------+--------------+------------------+
-- | podcastID | podcastName              | language | episodeCount | totalSubscribers |
-- +-----------+--------------------------+----------+--------------+------------------+
-- |         1 | The Joe Rogan Experience | English  |         1500 |          5000000 |
-- |         2 | Serial                   | English  |           50 |          2000000 |
-- |         3 | Crime Junkie             | English  |          200 |          1000000 |
-- |         4 | Radiolab                 | English  |          400 |           500000 |
-- |         5 | Pod Save America         | English  |          300 |           750000 |
-- |         6 | Stuff You Should Know    | English  |          800 |          1000000 |
-- |         7 | My Favorite Murder       | English  |          400 |           500000 |
-- |         8 | The Daily                | English  |         1000 |          3000000 |
-- +-----------+--------------------------+----------+--------------+------------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM primarygenre;
-- +----------+---------+
-- | artistID | genreID |
-- +----------+---------+
-- |        1 |       1 |
-- |        2 |       2 |
-- |        3 |       3 |
-- |        4 |       4 |
-- |        5 |       5 |
-- |        6 |       6 |
-- |        7 |       7 |
-- |        8 |       8 |
-- +----------+---------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM received;
-- +-----------+----------+
-- | paymentID | artistID |
-- +-----------+----------+
-- |         1 |        1 |
-- |         2 |        2 |
-- |         3 |        3 |
-- |         4 |        4 |
-- |         5 |        5 |
-- |         6 |        6 |
-- |         7 |        7 |
-- |         8 |        8 |
-- +-----------+----------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM recordlabel;
-- +---------------+--------------------------+
-- | recordLabelID | name                     |
-- +---------------+--------------------------+
-- |             1 | Universal Music Group    |
-- |             2 | Sony Music Entertainment |
-- |             3 | Warner Music Group       |
-- |             4 | EMI                      |
-- |             5 | Atlantic Records         |
-- |             6 | Capitol Records          |
-- |             7 | Republic Records         |
-- |             8 | Island Records           |
-- +---------------+--------------------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM recordlabelpayments;
-- +-----------+---------------+
-- | paymentID | paymentAmount |
-- +-----------+---------------+
-- |         1 |         10000 |
-- |         2 |          5000 |
-- |         3 |          7000 |
-- |         4 |          3000 |
-- |         5 |          8000 |
-- |         6 |          9000 |
-- |         7 |          2000 |
-- |         8 |          1000 |
-- +-----------+---------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM releasedin;
-- +--------+-----------+
-- | songID | countryID |
-- +--------+-----------+
-- |      1 |         2 |
-- |      2 |         5 |
-- |      3 |         1 |
-- |      4 |         4 |
-- |      5 |         6 |
-- |      6 |         3 |
-- |      7 |         2 |
-- |      8 |         5 |
-- +--------+-----------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM songs;
-- +--------+--------+----------+-------------+-------------+-------------+
-- | songID | title  | duration | releaseDate | royaltyPaid | royaltyRate |
-- +--------+--------+----------+-------------+-------------+-------------+
-- |      1 | Song A | 00:03:30 | 2022-01-01  |        1000 |         0.1 |
-- |      2 | Song B | 00:04:15 | 2022-01-02  |        1500 |        0.12 |
-- |      3 | Song C | 00:03:45 | 2022-01-03  |        2000 |        0.15 |
-- |      4 | Song D | 00:03:00 | 2022-01-04  |        2500 |        0.18 |
-- |      5 | Song E | 00:05:00 | 2022-01-05  |        3000 |         0.2 |
-- |      6 | Song F | 00:03:20 | 2022-01-06  |        3500 |        0.22 |
-- |      7 | Song G | 00:04:45 | 2022-01-07  |        4000 |        0.25 |
-- |      8 | Song H | 00:02:45 | 2022-01-08  |        4500 |         0.3 |
-- +--------+--------+----------+-------------+-------------+-------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM songsviewed;
-- +--------+------------+--------+
-- | songID | date       | count  |
-- +--------+------------+--------+
-- |      1 | 2022-01-01 | 100000 |
-- |      2 | 2022-01-01 | 200000 |
-- |      3 | 2022-01-01 | 300000 |
-- |      4 | 2022-01-01 | 400000 |
-- |      5 | 2022-01-01 | 500000 |
-- |      6 | 2022-01-01 | 600000 |
-- |      7 | 2022-01-01 | 700000 |
-- |      8 | 2022-01-01 | 800000 |
-- +--------+------------+--------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM sponsoredby;
-- +-----------+-----------+
-- | podcastID | sponsorID |
-- +-----------+-----------+
-- |         1 |         1 |
-- |         2 |         2 |
-- |         3 |         3 |
-- |         4 |         4 |
-- |         5 |         5 |
-- |         6 |         6 |
-- |         7 |         7 |
-- |         8 |         8 |
-- +-----------+-----------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM sponsors;
-- +-----------+-------------+
-- | sponsorID | sponsorName |
-- +-----------+-------------+
-- |         1 | Coca-Cola   |
-- |         2 | Pepsi       |
-- |         3 | McDonalds   |
-- |         4 | Burger King |
-- |         5 | KFC         |
-- |         6 | Samsung     |
-- |         7 | Apple       |
-- |         8 | Microsoft   |
-- +-----------+-------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM subscribeartist;
-- +--------+----------+
-- | userID | artistID |
-- +--------+----------+
-- |      1 |        1 |
-- |      1 |        2 |
-- |      2 |        3 |
-- |      2 |        4 |
-- |      3 |        5 |
-- |      3 |        6 |
-- |      3 |        7 |
-- |      3 |        8 |
-- +--------+----------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM subscribepodcast;
-- +--------+-----------+
-- | userID | podcastID |
-- +--------+-----------+
-- |      1 |         1 |
-- |      1 |         2 |
-- |      2 |         3 |
-- |      3 |         4 |
-- |      4 |         5 |
-- |      5 |         6 |
-- |      6 |         7 |
-- |      7 |         8 |
-- +--------+-----------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM sungby;
-- +----------+--------+
-- | artistID | songID |
-- +----------+--------+
-- |        1 |      1 |
-- |        1 |      4 |
-- |        1 |      7 |
-- |        2 |      2 |
-- |        2 |      5 |
-- |        2 |      8 |
-- |        3 |      3 |
-- |        3 |      6 |
-- +----------+--------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM sungin;
-- +--------+------------+
-- | songID | languageID |
-- +--------+------------+
-- |      1 |          1 |
-- |      1 |          2 |
-- |      2 |          1 |
-- |      2 |          3 |
-- |      3 |          2 |
-- |      4 |          4 |
-- |      5 |          3 |
-- |      6 |          2 |
-- +--------+------------+
-- 8 rows in set (0.000 sec)

-- MariaDB [nagaraj]> SELECT * FROM users;
-- +--------+--------------+--------------------------+------------------+------------------------+----------------------+-----------+----------+
-- | userID | phone        | email                    | registrationDate | monthlySubscriptionFee | statusOfSubscription | firstName | lastName |
-- +--------+--------------+--------------------------+------------------+------------------------+----------------------+-----------+----------+
-- |      1 | 1234567890   | user1@example.com        | 2022-01-01       |                   9.99 | active               | John      | Doe      |
-- |      2 | 0987654321   | user2@example.com        | 2021-03-15       |                   4.99 | inactive             | Jane      | Doe      |
-- |      3 | 1111111111   | user3@example.com        | 2020-10-01       |                  14.99 | active               | Bob       | Smith    |
-- |      4 | 555-555-5555 | john@example.com         | 2023-03-10       |                   9.99 | active               | John      | Doe      |
-- |      5 | 555-555-5555 | jane@example.com         | 2023-03-10       |                   9.99 | active               | Jane      | Doe      |
-- |      6 | 555-123-4567 | jane.doe@example.com     | 2022-01-01       |                   9.99 | Active               | Jane      | Doe      |
-- |      7 | 555-555-5555 | john.smith@example.com   | 2021-12-01       |                   5.99 | Active               | John      | Smith    |
-- |      8 | 555-987-6543 | susan.jones@example.com  | 2022-02-15       |                   7.99 | Active               | Susan     | Jones    |
-- |      9 | 555-111-2222 | jimmy.nguyen@example.com | 2022-03-01       |                   4.99 | Active               | Jimmy     | Nguyen   |
-- |     10 | 555-555-1212 | kim.wong@example.com     | 2022-01-15       |                   6.99 | Active               | Kim       | Wong     |
-- +--------+--------------+--------------------------+------------------+------------------------+----------------------+-----------+----------+
-- 10 rows in set (0.000 sec)

-- MariaDB [nagaraj]>

-- 1. Information Processing: Enter/update/delete basic information about songs, artists, podcast hosts, and podcast episodes. 
-- Assign songs and artists to albums. Assign artists to record labels. Assign podcast episodes and podcast hosts to podcasts.
    -- To insert a new song:
    INSERT INTO songs (songID, title, duration, releaseDate, royaltyPaid, royaltyRate)
    VALUES (11, 'songTitle', '00:00:02', '2022-03-01', 2.2, 1);

    -- To update an existing song:
    UPDATE songs
    SET title = 'newTitle', duration = '00:00:02', releaseDate = '2022-03-01', royaltyPaid = 2.2, royaltyRate = 3
    WHERE songID = 11;

    -- To delete a song:
    DELETE FROM songs
    WHERE songID = 11;

    -- To insert a new artist:
    INSERT INTO artists (artistID, name, status, type)
    VALUES (11, 'artistName', 'artistStatus', 'artistType');

    -- To update an existing artist:
    UPDATE artists
    SET name = 'newName', status = 'newStatus', type = 'newType';

    -- To delete an artist:
    DELETE FROM artists
    WHERE artistID = 11;

    -- To insert a new podcast host:
    INSERT INTO podcastHosts (podcastHostID, firstName, lastName, phone, email, city)
    VALUES (11, 'newName', 'newName', '123-456-7890', 'john.doe@example.com', 'new city');

    -- To update an existing podcast host:
    UPDATE podcastHosts
    SET firstName = 'new first name'
    WHERE podcastHostID = 11;

    -- To delete a podcast host:
    DELETE FROM podcastHosts
    WHERE podcastHostID = 11;

    -- To insert a new podcast episode:
    INSERT INTO podcastEpisodes (podcastEpisodeID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) 
    VALUES (11, 'new episode name', '00:03:30', '2023-01-01', 23, 100);

    -- To update an existing podcast episode:
    UPDATE podcastEpisodes
    SET episodeTitle = 'newTitle', duration = '00:05:30', releaseDate = '2023-01-03', listeningCount = 12, advertisementCount = 123
    WHERE podcastEpisodeID = 11;

    -- To delete a podcast episode:
    DELETE FROM podcastEpisodes
    WHERE podcastEpisodeID = 11;

    -- To assign songs to albums:

    -- create song and album then assign song to album
    INSERT INTO songs (songID, title, duration, releaseDate, royaltyPaid, royaltyRate)
    VALUES (111, 'add song to album', '00:05:30', '2023-01-03', 100, 23);

    INSERT INTO albums (albumID, name, edition, releaseYear) 
    VALUES (12, 'Album 12', 'Deluxe Edition -12 ', 2020);

    INSERT INTO belongsTo (albumID, songID, trackNumber) 
    VALUES (12, 111, 7);

    -- To assign artists to record labels:
    INSERT INTO artists (artistID, name, status, type)
    VALUES (11, 'artistName', 'artistStatus', 'artistType');

    INSERT INTO recordLabel (recordLabelID, name) 
    VALUES (12, 'Universal Music Group');

    INSERT INTO contractedWith (artistID, recordLabelID)
    VALUES (11, 12);

    -- To assign podcast episodes to podcasts:
    INSERT INTO podcasts (podcastID, podcastName, language, episodeCount, totalSubscribers, rating) 
    VALUES (12, 'The Joe Rogan Experience', 'English', 1500, 5000000, 5);

    INSERT INTO podcastEpisodes (podcastEpisodeID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) 
    VALUES (11, 'new episode name', '00:03:30', '2023-01-01', 23, 100);

    INSERT INTO partOf (podcastID, podcastEpisodeID)
    VALUES (12, 11);


    -- To assign podcast hosts to podcast episodes:
    INSERT INTO podcastHosts (podcastHostID, firstName, lastName, phone, email, city)
    VALUES (15, 'newName', 'newName', '123-456-7890', 'john.doe@example.com', 'new city');

    INSERT INTO podcasts (podcastID, podcastName, language, episodeCount, totalSubscribers, rating) 
    VALUES (13, 'The Joe Rogan Experience', 'English', 1500, 5000000, 1);

    INSERT INTO ownedBy (podcastID, podcastHostID)
    VALUES (13, 15);

    INSERT INTO podcastEpisodes (podcastEpisodeID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) 
    VALUES (23, 'podcast episode 23', '00:03:30', '2023-01-01', 23, 100);

    INSERT INTO partOf (podcastID, podcastEpisodeID)
    VALUES (13, 23);

-- 2. Maintaining metadata and records:  Enter/update play count for songs.  Enter/update the count of monthly listeners for artists. 
-- Enter/update the total count of subscribers and ratings for podcasts. Enter/Update the listening count for podcast episodes.
--  Find songs and podcast episodes given artist, album, and/or podcast.

    -- NOTE:  we store every date as month's first date, i.e, if a song or monthly listeners artist is to incremented, then we increment the record assoicated with date of current month
    
    
    -- Enter/update play count for songs

    -- create two songs:
        INSERT INTO songs (songID, title, duration, releaseDate, royaltyPaid, royaltyRate) VALUES 
        (16, 'song 16', '00:05:30', '2023-01-03', 100, 23),
        (17, 'song 17', '00:05:30', '2023-01-03', 100, 23);

    -- create the corresponsing number of times songs viewed 
        INSERT INTO songsViewed (songID, date, count) VALUES 
        (16, '2022-03-01', 0),
        (17, '2022-03-01', 0);
        
    -- increment the song's view count for the current month
        UPDATE songsViewed SET count = count + 1 WHERE date = DATE_FORMAT(NOW() ,'%Y-%m-01') AND songID IN  (16, 17);

    -- Enter/update the count of monthly listeners for artists
        
        -- create two artists records:
        INSERT INTO artists (artistID, name, status, type) VALUES
        (16, 'artistName', 'artistStatus', 'artistType'),
        (17, 'artistName', 'artistStatus', 'artistType');

        -- create monthly listener counts for corresponsing artists 
        INSERT INTO monthlyListeners (artistID, date, count) VALUES 
        (16, '2022-03-01', 0),
        (17, '2022-03-01', 0);

        -- increment the artists's monthly listener's count for the current month
        UPDATE monthlyListeners SET count = count + 1 WHERE artistID in  (16, 17) and date = DATE_FORMAT(NOW() ,'%Y-%m-01');

    -- Enter/update the total count of subscribers and ratings for podcasts

        -- create podcasts:
        INSERT INTO podcasts (podcastID, podcastName, language, episodeCount, totalSubscribers, rating) VALUES 
        (16, 'The Joe Rogan Experience-16', 'English', 1500, 5000000, 5),
        (17, 'The Joe Rogan Experience-17', 'English', 1500, 5000000, 3);

        -- update the total subscribers and rating of the corresponding podcasts
        UPDATE podcasts SET totalSubscribers = 100, rating=1  WHERE podcastID in  (16, 17);

    -- Enter/Update the listening count for podcast episodes.

        -- create podcast episodes
        INSERT INTO podcastEpisodes (podcastEpisodeID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) VALUES 
        (16, 'new episode name-16', '00:03:31', '2023-01-01', 992, 101),
        (17, 'new episode name-17', '00:03:01', '2022-01-01', 200, 11);

        -- update the listening count for the above podcast episodes accordingly
        UPDATE podcastEpisodes SET listeningCount = 100 WHERE podcastEpisodeID in  (16, 17);

    -- Find songs and podcast episodes given artist, album, and/or podcast.
        
        -- create records in all related tables in order to perform search functionality:

        -- create songs:
        INSERT INTO songs (songID, title, duration, releaseDate, royaltyPaid, royaltyRate) VALUES 
        (25, 'James Song', '00:05:30', '2023-01-03', 100, 23),
        (26, 'Yasodha Song', '00:05:30', '2023-01-03', 100, 23);

        -- create artists:
        INSERT INTO artists (artistID, name, status, type) VALUES
        (26, 'James', 'artistStatus', 'artistType'),
        (27, 'Yasodha', 'artistStatus', 'artistType');
        
        -- create albums:
        INSERT INTO albums (albumID, name, edition, releaseYear) VALUES
        (31, 'James Album', 'Deluxe Edition', 2020),
        (32, 'Yosodha Album', 'Standard Edition', 2018);
        
        -- link songs to artist
        INSERT INTO sungBy (artistID, songID) VALUES
        (26, 25),
        (27, 26);

        -- link collaborators to the songs
        INSERT INTO collaboratedBy (artistID, songID) VALUES
        (27, 25),
        (26, 26);

        -- link artists to albums:
        INSERT INTO has (artistID, albumID) VALUES
        (26, 31),
        (27, 32);

        -- link songs to corresponding album:
        INSERT INTO belongsTo (albumID, songID, trackNumber) VALUES
        (31, 25, 643),
        (32, 26, 996);

        -- create podcasts
        INSERT INTO podcasts (podcastID, podcastName, language, episodeCount, totalSubscribers, rating) VALUES 
        (26, 'Podcast-26', 'English', 1500, 5000000, 5),
        (27, 'Podcast-27', 'English', 1500, 5000000, 3);

        -- create podcast episodes
        INSERT INTO podcastEpisodes (podcastEpisodeID, episodeTitle, duration, releaseDate, listeningCount, advertisementCount) VALUES 
        (46, 'PodcastEps-46', '00:03:31', '2023-01-01', 992, 101),
        (47, 'PodcastEps-47', '00:03:01', '2022-01-01', 200, 11),
        (48, 'PodcastEps-48', '00:03:31', '2023-01-01', 992, 101),
        (49, 'PodcastEps-49', '00:03:01', '2022-01-01', 200, 11);

        -- link podcast episodes to the corresponding podcast
        INSERT INTO partOf (podcastID, podcastEpisodeID)
        VALUES (26, 46), (26, 47),(27, 48),(27,49);

        -- implementing search functionality:


        -- 1. To find all songs by an artist(Yasodha):
          SELECT songs.* 
          FROM songs 
          JOIN sungBy ON songs.songID = sungBy.songID 
          JOIN artists ON sungBy.artistID = artists.artistID 
          WHERE artists.name = 'Yasodha';

        -- 2. To find all songs on an album(James Album):
          SELECT songs.* 
          FROM songs 
          JOIN belongsTo ON songs.songID = belongsTo.songID 
          JOIN albums ON belongsTo.albumID = albums.albumID 
          WHERE albums.name = 'James Album';
        
        -- 3. To find all podcast episodes for a specific podcast(Podcast-26):
          SELECT podcastEpisodes.* 
          FROM podcastEpisodes 
          JOIN partOf ON podcastEpisodes.podcastEpisodeID = partOf.podcastEpisodeID 
          JOIN podcasts ON partOf.podcastID = podcasts.podcastID 
          WHERE podcasts.podcastName = 'Podcast-26';

-- 4.1.3. Maintaining payments: Make royalty payments for a given song. Monthly royalties are generated based on a royalty rate 
-- for each song times the total play count per month. 30% of the collected royalties are paid to the record label and 
-- the remainder is distributed evenly among all participating artists. Make payment to podcast hosts.
-- Podcast hosts are paid a single flat fee per released episode and an additional bonus based on total advertisements per 
-- podcast episode. Receive payment from subscribers.
--TODO podcast payments and user payments


  --Royalty payments for a given song, we first insert data into the master payments table, then link the paymentID to the child relations recordLabelPayments, userPayments, artistPayments,podcastHostPayments; then link these records in child payment tables to the corresponding entities with  corresponding relation tables such as paymentsReceived,paymentsMade,received, podcastPayments:

  -- insert into master payments table
    INSERT INTO payments (paymentID,date) VALUES (101,  DATE_FORMAT(NOW() ,'%Y-%m-01'));
    
  -- insert record label payment for a given song, it will be 30% of the collected royalties.
    INSERT INTO recordLabelPayments (paymentID, paymentAmount)
    select 101 as paymentID, songsViewed.count * songs.royaltyRate * 0.3 as paymentAmount
    from songs
    join songsViewed ON songs.songID= songsViewed.songID
    where songs.songID=1 AND songsViewed.date=DATE_FORMAT(NOW() ,'%Y-%m-01');
  -- Link the above payment with the corresponding song in the song's relationship table.
    INSERT INTO paymentsReceived (paymentID, recordLabelID)
    select 101 as paymentID, r.recordLabelID as recordLabelID
    FROM songs s
    JOIN sungBy sb ON s.songID = sb.songID 
    JOIN artists a ON a.artistID = sb.artistID
    JOIN contractedWith c ON c.artistID = a.artistID
    JOIN recordLabel r ON c.recordLabelID = r.recordLabelID
    WHERE s.songID = 1;

  -- insert artist payments for the corresponding song, insert the payment to the artist and all the collaborators equally
    INSERT INTO artistPayments (paymentID, paymentAmount)
    select 101 as paymentID, (songsViewed.count * s.royaltyRate) * 0.7/ count(*)
    FROM songs s 
    JOIN belongsTo b ON s.songID = b.songID 
    JOIN albums a ON b.albumID = a.albumID 
    JOIN has h ON a.albumID = h.albumID 
    JOIN contractedWith c ON h.artistID = c.artistID 
    JOIN recordLabel r ON c.recordLabelID = r.recordLabelID
    join songsViewed ON s.songID= songsViewed.songID
    WHERE s.songID = 1;

    -- Link the above payment of the song for all the collaborators  in the artists's payment relationship table.
    INSERT INTO received (paymentID, artistID)
    SELECT 101 as paymentID, c.artistID 
    FROM songs s 
    JOIN collaboratedBy c ON c.songID = s.songID 
    WHERE s.songID = 1;
    
     -- Link the above payment of the song for the main artist in the artists's payment relationship table.
    INSERT INTO received (paymentID, artistID)
    SELECT 101 as paymentID, sb.artistID 
    FROM songs s
    JOIN sungBy sb ON s.songID = sb.songID
    WHERE s.songID = 1;
    
-- Make payment to podcast hosts. Podcast hosts are paid a single flat fee per released episode and an additional bonus 
-- based on total advertisements per podcast episode

    -- insert the master payment for episode 1
    INSERT INTO payments (paymentID,date) VALUES (202,  DATE_FORMAT(NOW() ,'%Y-%m-01'));
    
    -- insert the above payment to the podcasthost payments table along with a constant flat fee and  variable bonus
    INSERT INTO podcastHostPayments (paymentID, flatFee, bonus)
    select 202 as paymentID, 10 as flatFee,( p.rating * ps.advertisementCount) as bonus
    FROM podcasts p
    JOIN partOf po ON po.podcastID=p.podcastID
    JOIN podcastEpisodes ps ON ps.podcastEpisodeID = po.podcastEpisodeID
    WHERE ps.podcastEpisodeID = 1;
    
    -- now link the above payment to the podcasthost
    INSERT INTO podcastPayments (paymentID, podcastHostID)
    SELECT 202 as paymentID, ph.podcastHostID as podcastHostID
    FROM podcasts p
    JOIN ownedBy o ON o.podcastID = p.podcastID
    JOIN  podcastHosts ph ON ph.podcastHostID = o.podcastHostID
    JOIN partOf po ON po.podcastID=p.podcastID
    JOIN podcastEpisodes ps ON ps.podcastEpisodeID = po.podcastEpisodeID
    WHERE ps.podcastEpisodeID = 1;

-- Receive payment from subscribers. Here users are subscribers

    -- insert master payment for user payment
    INSERT INTO payments (paymentID,date) VALUES (110,  DATE_FORMAT(NOW() ,'%Y-%m-01'));

    -- insert the payment amount to the user payments which is the child of master payments, which is the monthly subscription fee, we  are storing the payment amount because the monthly subscription  fee may change and storing this would enable us to know how much it was charged for each month.
    INSERT INTO userPayments (paymentID, paymentAmount)
    select 110 as paymentID, u.monthlySubscriptionFee as paymentAmount
    FROM users u
    WHERE u.userID = 1;

    -- linking the above user's payment to the users table
    INSERT INTO paymentsMade (paymentID, userID)
    SELECT 101 as paymentID, u.userID as userID
    FROM users u
    WHERE u.userID = 1;


-- 4.1.4. Reports: Generate all the following reports: Monthly play count per song/album/artist. Calculate total payments made
-- out to host/artist/record labels per a given time period. Total revenue of the streaming service per month, per year. 
-- Report all songs/podcast episodes given an artist, album, and/or podcast.

  -- 1. Monthly play count per song/album/artist
  -- songs
    SELECT s.songID, s.title, YEAR(sv.date) AS year, MONTH(sv.date) AS month, SUM(sv.count) AS play_count
    FROM songs s
    INNER JOIN songsViewed sv ON s.songID = sv.songID
    where s.songID=1
    GROUP BY s.songID, YEAR(sv.date), MONTH(sv.date);
  -- albums
    SELECT a.albumID, YEAR(sv.date) AS year, MONTH(sv.date) AS month, SUM(sv.count) AS play_count
    FROM albums a
    INNER JOIN belongsTo bt ON a.albumID = bt.albumID
    INNER JOIN songsViewed sv ON bt.songID = sv.songID
    where a.albumID = ?
    GROUP BY a.albumID, YEAR(sv.date), MONTH(sv.date);
  -- artist
    SELECT ar.name, YEAR(ml.date) AS year, MONTH(ml.date) AS month, SUM(ml.count) AS play_count
    FROM artists ar
    INNER JOIN monthlyListeners ml ON ar.artistID = ml.artistID
    where ar.artistID= ? 
    GROUP BY ar.artistID, YEAR(ml.date), MONTH(ml.date);

  -- 2. Calculate total payments made out to host/artist/record labels per a given time period
    -- host
      SELECT podcastHosts.firstName, SUM(podcastHostPayments.flatFee + podcastHostPayments.bonus) AS totalPayments
      FROM podcastHostPayments
      JOIN podcastPayments ON podcastHostPayments.paymentID = podcastPayments.paymentID
      JOIN payments ON podcastHostPayments.paymentID = payments.paymentID
      JOIN podcastHosts ON podcastHosts.podcastHostID = podcastPayments.podcastHostID
      WHERE 
      podcastHosts.podcastHostID= ? AND 
      payments.date BETWEEN '2022-01-01' AND '2022-12-31'
      GROUP BY podcastHosts.podcastHostID;

    -- artist
      SELECT artists.name, SUM(artistPayments.paymentAmount) AS total_payments
      FROM artistPayments
      JOIN received ON artistPayments.paymentID = received.paymentID
      JOIN artists ON received.artistID = artists.artistID
      JOIN payments ON artistPayments.paymentID = payments.paymentID
      WHERE payments.date BETWEEN '2022-01-01' AND '2022-12-31'
      GROUP BY artists.artistID;


    -- record label
      SELECT recordLabel.name, SUM(paymentAmount) AS total_payments
      FROM recordLabelPayments
      JOIN paymentsReceived ON paymentsReceived.paymentID = recordLabelPayments.paymentID
      JOIN payments ON recordLabelPayments.paymentID = payments.paymentID
      JOIN recordLabel ON recordLabel.recordLabelID = paymentsReceived.recordLabelID
      WHERE recordLabel.id= ?
      payments.date BETWEEN ? AND ? 
      GROUP BY recordLabel.name;

  -- 3. Total revenue of the streaming service per month, per year
    --Total revenue per month:
      SELECT DATE_FORMAT(date, '%Y-%m') AS month, SUM( recordLabelPayments.paymentAmount)+  sum(artistPayments.paymentAmount) +  sum(userPayments.paymentAmount) AS total_revenue
      FROM managedBy
      JOIN payments ON managedBy.paymentID = payments.paymentID
      JOIN recordLabelPayments ON payments.paymentID = recordLabelPayments.paymentID
      JOIN artistPayments ON payments.paymentID = artistPayments.paymentID
      JOIN userPayments ON payments.paymentID = userPayments.paymentID
      GROUP BY DATE_FORMAT(date, '%Y-%m');

    --Total revenue per year:
      SELECT YEAR(date) AS year,  SUM( recordLabelPayments.paymentAmount)+  sum(artistPayments.paymentAmount) +  sum(userPayments.paymentAmount) AS total_revenue
      FROM managedBy
      JOIN payments ON managedBy.paymentID = payments.paymentID
      JOIN recordLabelPayments ON payments.paymentID = recordLabelPayments.paymentID
      JOIN artistPayments ON payments.paymentID = artistPayments.paymentID
      JOIN userPayments ON payments.paymentID = userPayments.paymentID
      GROUP BY YEAR(date);

-- 4. Report all songs/podcast episodes given an artist, album, and/or podcast.
    -- songs given artist
      SELECT songs.title 
      FROM songs 
      INNER JOIN sungBy ON songs.songID = sungBy.songID 
      INNER JOIN artists ON sungBy.artistID = artists.artistID 
      WHERE artists.name = 'James';

    -- songs given album
      SELECT songs.title 
      FROM songs
      INNER JOIN belongsTo ON songs.songID = belongsTo.songID 
      INNER JOIN albums ON belongsTo.albumID = albums.albumID 
      WHERE albums.name = 'James Album';

    -- podcast episodes given podcast
      SELECT podcastEpisodes.episodeTitle 
      FROM podcastEpisodes 
      INNER JOIN partOf ON podcastEpisodes.podcastEpisodeID = partOf.podcastEpisodeID 
      INNER JOIN podcasts ON partOf.podcastID = podcasts.podcastID 
      WHERE podcasts.podcastName = 'Podcast-26';

-- 4.2 (70 points). Use the EXPLAIN directive in MariaDB to study execution plans for your queries (from item 4.1 above). 
-- Find two queries for which EXPLAIN shows full table scans; print the EXPLAIN outputs for these queries. 
-- For these two queries, create appropriate indexes; print the EXPLAIN output that shows the use of the indexes. 
-- For each query, submit the printouts of: (1) the SQL query, (2) the execution plan (EXPLAIN) for the query which shows
--  at least one full table scan, (3) your index-creation statement in SQL, and (4) the execution plan (EXPLAIN) for the 
-- query where the full table scan has been replaced with a use of your index.

  --Query1:
      SELECT songs.*
      FROM songs 
      JOIN sungBy ON songs.songID = sungBy.songID 
      JOIN artists ON sungBy.artistID = artists.artistID 
      WHERE artists.name = 'Yasodha';
--       MariaDB [nagaraj]> EXPLAIN SELECT songs.*
--     ->           FROM songs
--     ->           JOIN sungBy ON songs.songID = sungBy.songID
--     ->           JOIN artists ON sungBy.artistID = artists.artistID
--     ->           WHERE artists.name = 'Yasodha';
-- +------+-------------+---------+--------+------------------+----------+---------+-------------------------+------+--------------------------+
-- | id   | select_type | table   | type   | possible_keys    | key      | key_len | ref                     | rows | Extra
--   |
-- +------+-------------+---------+--------+------------------+----------+---------+-------------------------+------+--------------------------+
-- |    1 | SIMPLE      | sungBy  | index  | PRIMARY,artistID | artistID | 5       | NULL                    | 10   | Using where; Using index |
-- |    1 | SIMPLE      | songs   | eq_ref | PRIMARY          | PRIMARY  | 4       | nagaraj.sungBy.songID   | 1    |
--   |
-- |    1 | SIMPLE      | artists | eq_ref | PRIMARY          | PRIMARY  | 4       | nagaraj.sungBy.artistID | 1    | Using where
--   |
-- +------+-------------+---------+--------+------------------+----------+---------+-------------------------+------+--------------------------+
-- 3 rows in set (0.001 sec)

  --Query2:
      SELECT podcastEpisodes.episodeTitle 
      FROM podcastEpisodes 
      INNER JOIN partOf ON podcastEpisodes.podcastEpisodeID = partOf.podcastEpisodeID 
      INNER JOIN podcasts ON partOf.podcastID = podcasts.podcastID 
      WHERE podcasts.podcastName = 'Podcast-26';
      