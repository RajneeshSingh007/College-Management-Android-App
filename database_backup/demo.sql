-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2017 at 12:01 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `atdsp`
--

CREATE TABLE `atdsp` (
  `id` int(100) NOT NULL,
  `atrollno` varchar(100) DEFAULT NULL,
  `atdate` date DEFAULT NULL,
  `atstatus` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `atdsp`
--

INSERT INTO `atdsp` (`id`, `atrollno`, `atdate`, `atstatus`) VALUES
(141, '22', '2017-03-25', 'present'),
(140, '14', '2017-03-25', 'present'),
(139, '18', '2017-03-25', 'present'),
(138, '10', '2017-03-25', 'present'),
(137, '14', '2017-03-25', 'present'),
(136, '2', '2017-03-24', 'present'),
(135, '4', '2017-03-24', 'present'),
(134, '3', '2017-03-24', 'present'),
(133, '2', '2017-03-24', 'present'),
(132, '1', '2017-03-24', 'present'),
(131, '1', '2017-03-23', 'present'),
(130, '1', '2017-03-23', 'present'),
(129, '3', '2017-03-23', 'present'),
(128, '1', '2017-03-23', 'present'),
(127, '4', '2017-03-23', 'present'),
(126, '2', '2017-03-23', 'present'),
(125, '3', '2017-03-23', 'present'),
(124, '2', '2017-03-23', 'present'),
(123, '4', '2017-03-23', 'present'),
(122, '1', '2017-03-23', 'present'),
(142, '5', '2017-04-02', 'present'),
(143, '14', '2017-04-02', 'present'),
(144, '1', '2017-04-03', 'present');

-- --------------------------------------------------------

--
-- Table structure for table `atmc`
--

CREATE TABLE `atmc` (
  `id` int(100) NOT NULL,
  `atrollno` varchar(100) DEFAULT NULL,
  `atdate` date DEFAULT NULL,
  `atstatus` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL,
  `atdate` date DEFAULT NULL,
  `atrolls` varchar(110) DEFAULT NULL,
  `atstatus` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`id`, `atdate`, `atrolls`, `atstatus`) VALUES
(1, '2017-03-21', '1,2,3,4,5', 'present'),
(2, '2017-03-21', '10,20,30,40,50', 'present'),
(3, '2017-03-22', '1,2,3,4,5', 'present');

-- --------------------------------------------------------

--
-- Table structure for table `civilupload`
--

CREATE TABLE `civilupload` (
  `id` int(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cmpnupload`
--

CREATE TABLE `cmpnupload` (
  `id` int(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cmpnupload`
--

INSERT INTO `cmpnupload` (`id`, `name`, `url`) VALUES
(1, 'JPEG_20170308_001359_1203720453.jpg', 'http://192.168.186.2/demo/uploads/JPEG_20170308_001359_1203720453.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `dsp`
--

CREATE TABLE `dsp` (
  `id` int(11) NOT NULL,
  `chaptername` varchar(100) NOT NULL,
  `workingdata` int(1) NOT NULL,
  `checked` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dsp`
--

INSERT INTO `dsp` (`id`, `chaptername`, `workingdata`, `checked`) VALUES
(1, 'Discrete Time Signal', 1, '2017-04-02 21:59:58'),
(2, 'Z-Transform', 1, '2017-04-02 21:47:41'),
(3, 'Discrete Fourier Transform', 1, '2017-04-02 21:47:43'),
(4, 'Introduction to Digital Image Processing System', 0, '2017-04-02 21:40:33'),
(5, 'Image Transform', 1, '2017-04-02 21:59:58'),
(6, 'Image Enhancement', 1, '2017-04-02 22:00:30'),
(7, 'Image Restoration andDenoising', 0, '2017-04-02 21:40:55'),
(9, 'Image Data Compression', 0, '2017-03-08 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `etrxupload`
--

CREATE TABLE `etrxupload` (
  `id` int(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `extcupload`
--

CREATE TABLE `extcupload` (
  `id` int(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gupload`
--

CREATE TABLE `gupload` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gupload`
--

INSERT INTO `gupload` (`id`, `name`, `url`) VALUES
(15, '/storage/emulated/0/Pictures/slrtce/JPEG_2017-03-22 20:16:58_-130395813.jpg', 'http://192.168.112.2/demo/uploads/JPEG_2017-03-22 20:16:58_-130395813.jpg'),
(14, '/storage/emulated/0/ActionPlus/ED_Hall_Tickets_Dec_2016.pdf', 'http://192.168.112.2/demo/uploads/ED_Hall_Tickets_Dec_2016.pdf'),
(13, '/storage/emulated/0/Pictures/slrtce/JPEG_2017-03-22 20:15:30_-1698467414.jpg', 'http://192.168.112.2/demo/uploads/JPEG_2017-03-22 20:15:30_-1698467414.jpg'),
(12, '/storage/emulated/0/Download/[extreme7.blogspot.com]3D_LandScape-Wallpapers  (9).jpg', 'http://192.168.240.166/demo/uploads/[extreme7.blogspot.com]3D_LandScape-Wallpapers  (9).jpg'),
(11, '/storage/emulated/0/Download/0001.jpg', 'http://192.168.240.166/demo/uploads/0001.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `itupload`
--

CREATE TABLE `itupload` (
  `id` int(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `mc`
--

CREATE TABLE `mc` (
  `id` int(10) NOT NULL,
  `chaptername` varchar(40) NOT NULL,
  `workingdata` int(1) NOT NULL,
  `checked` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mc`
--

INSERT INTO `mc` (`id`, `chaptername`, `workingdata`, `checked`) VALUES
(1, 'Introduction To MC', 0, '2017-03-08 16:38:37'),
(2, 'Telecommunication Systems I', 0, '2017-03-08 16:40:27'),
(3, 'Telecommunication System II', 0, '2017-03-08 16:44:15'),
(4, 'Satellite System', 0, '2017-03-08 16:45:44'),
(5, 'Wireless LAN', 0, '2017-03-08 16:46:48'),
(6, 'Wirless ATM', 0, '2017-03-08 16:48:47'),
(7, 'Mobile Network and Transport Layer', 0, '2017-03-08 16:50:26'),
(8, 'Support for Mobility', 0, '2017-03-08 16:54:13');

-- --------------------------------------------------------

--
-- Table structure for table `mechupload`
--

CREATE TABLE `mechupload` (
  `id` int(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nonteacher`
--

CREATE TABLE `nonteacher` (
  `id` int(11) NOT NULL,
  `unique_id` varchar(23) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `active` int(1) DEFAULT NULL,
  `HASH` varchar(32) DEFAULT NULL,
  `fullname` varchar(50) DEFAULT NULL,
  `nonteachid` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nonteacher`
--

INSERT INTO `nonteacher` (`id`, `unique_id`, `username`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`, `active`, `HASH`, `fullname`, `nonteachid`) VALUES
(2, '58badbf20b3637.99742674', 'nteach', 'nteach@gmail.com', '5tyKeNK7f/EXk5xyZ3JzrVbamLMyZTJhN2ZiNzU1', '2e2a7fb755', '2017-03-04 20:53:30', NULL, 1, '3c59dc048e8850243be8079a5c74d079', 'nteach', '498');

-- --------------------------------------------------------

--
-- Table structure for table `pm`
--

CREATE TABLE `pm` (
  `id` int(10) NOT NULL,
  `chaptername` varchar(60) NOT NULL,
  `workingdata` int(1) NOT NULL,
  `checked` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pm`
--

INSERT INTO `pm` (`id`, `chaptername`, `workingdata`, `checked`) VALUES
(1, 'Intro to Project management', 1, '2017-04-02 22:01:09'),
(2, 'Project management and It context', 0, '2017-03-08 17:16:26'),
(3, 'Project Inegration Management', 0, '2017-03-08 17:18:26'),
(4, 'Project Scope management', 1, '2017-04-02 22:01:09'),
(5, 'Project Time Management', 0, '2017-03-08 17:20:43'),
(6, 'Project cost management', 0, '2017-03-08 17:21:37'),
(7, 'Project Quality management', 0, '2017-03-08 17:23:01'),
(8, 'Project Resource management', 0, '2017-03-08 17:26:17'),
(9, 'Project Communication management', 0, '2017-03-08 17:27:34'),
(10, 'Project Risk management', 0, '2017-03-08 17:29:01'),
(11, 'Project Procurement management', 0, '2017-03-08 17:30:22');

-- --------------------------------------------------------

--
-- Table structure for table `sc`
--

CREATE TABLE `sc` (
  `id` int(10) NOT NULL,
  `chaptername` varchar(60) NOT NULL,
  `workingdata` int(1) NOT NULL,
  `checked` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sc`
--

INSERT INTO `sc` (`id`, `chaptername`, `workingdata`, `checked`) VALUES
(1, 'Fuzzy Set Theory', 0, '2017-03-08 17:34:21'),
(2, 'Optimization', 1, '2017-03-11 00:14:00'),
(3, 'Neural network', 1, '2017-03-11 00:13:59'),
(4, 'Neuro Fuzzy Modeling', 1, '2017-03-11 00:13:59'),
(5, 'Application of computational intelligence', 1, '2017-03-11 00:13:59');

-- --------------------------------------------------------

--
-- Table structure for table `ss`
--

CREATE TABLE `ss` (
  `id` int(10) NOT NULL,
  `chaptername` varchar(60) NOT NULL,
  `workingdata` int(1) NOT NULL,
  `checked` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ss`
--

INSERT INTO `ss` (`id`, `chaptername`, `workingdata`, `checked`) VALUES
(1, 'Introduction to Information Security', 1, '2017-03-11 00:14:05'),
(2, 'Cryptography', 1, '2017-03-11 00:14:05'),
(3, 'Access control-Authentication and Authorization', 1, '2017-03-11 00:14:06'),
(4, 'Software security', 1, '2017-03-11 00:14:02'),
(5, 'Network Security', 1, '2017-03-11 00:14:02');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `unique_id` varchar(23) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `grno` varchar(10000) NOT NULL,
  `hash` varchar(32) NOT NULL,
  `active` int(1) NOT NULL DEFAULT '0',
  `fullname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='student login system';

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `unique_id`, `username`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`, `grno`, `hash`, `active`, `fullname`) VALUES
(241, '58d3aaef293021.18490810', 'asdfg', 'qwerty@gmail.com', 'mB/nORN8/Fl3AN1ot5X/x7tMDIVlN2ZiN2RjMGE3', 'e7fb7dc0a7', '2017-03-23 16:31:03', NULL, '9049', '2f2b265625d76a6704b08093c652fd79', 1, 'asdfg'),
(242, '58d3b2662b9670.82438282', 'shweta', 'nbcchdbc@ga.com', 'mYAx8BloQeCn9N9ZcZ21tWnAia40MDcyZGQwNzRj', '4072dd074c', '2017-03-23 17:02:54', NULL, '1681', 'fb89705ae6d743bf1e848c206e16a1d7', 1, 'sdbjhhbcsb'),
(243, '58d3b2f6623381.45647320', 'shweta1', 'sjsxbsgcxu@gmail.com', '0ve66F/dy+MY53a1s+Yc6DFji1ljYzZhNGNmYWEx', 'cc6a4cfaa1', '2017-03-23 17:05:18', NULL, '3201', 'a760880003e7ddedfef56acb3b09697f', 0, 'jhfurhfuhnun'),
(244, '58d41115c2c2d0.09275582', 'mrcool', 'mrcool@gmail.com', 'E3gowZHte9bmhQDQ6UiOxndPbN1lNGI0ZWRiNWU1', 'e4b4edb5e5', '2017-03-23 23:46:53', NULL, '7595', 'aab3238922bcc25a6f606eb525ffdc56', 1, 'coolalien'),
(245, '58f9e6dc4bf434.67277040', 'asdfghj', 'coolalien01@gmail.com', 'BdFYWursW9Rh3yq6YHmmRz9nAj1iNDUwNDU2YzZm', 'b450456c6f', '2017-04-21 16:32:52', NULL, '6038', 'c16a5320fa475530d9583c34fd356ef5', 0, 'coolz');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `unique_id` varchar(23) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `active` int(1) DEFAULT NULL,
  `HASH` varchar(32) DEFAULT NULL,
  `fullname` varchar(50) DEFAULT NULL,
  `teacherid` varchar(10000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`id`, `unique_id`, `username`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`, `active`, `HASH`, `fullname`, `teacherid`) VALUES
(25, '58baae3dc0a1b2.86252906', 'teacher', 'xcvbn@gmail.com', 'NC7fBqpja78cy8sDppeGG5wEuGwwOGNjZDAyN2Y1', '08ccd027f5', '2017-03-04 17:38:29', NULL, 1, 'b56a18e0eacdf51aa2a5306b0f533204', 'teacher', '98526'),
(26, '58d3b97150de07.33547013', 'manju', 'sncbhavchd@gmail.com', 'tctU1rXZY6YVO2K8/MtVQ1zuPJZiN2ZjNTg2NDc2', 'b7fc586476', '2017-03-23 17:32:57', NULL, 1, '58ae749f25eded36f486bc85feb3f0ab', 'sbsddhydc', '67152');

-- --------------------------------------------------------

--
-- Table structure for table `upload`
--

CREATE TABLE `upload` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `atdsp`
--
ALTER TABLE `atdsp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `atmc`
--
ALTER TABLE `atmc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `civilupload`
--
ALTER TABLE `civilupload`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cmpnupload`
--
ALTER TABLE `cmpnupload`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dsp`
--
ALTER TABLE `dsp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `etrxupload`
--
ALTER TABLE `etrxupload`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `extcupload`
--
ALTER TABLE `extcupload`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gupload`
--
ALTER TABLE `gupload`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `itupload`
--
ALTER TABLE `itupload`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mc`
--
ALTER TABLE `mc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mechupload`
--
ALTER TABLE `mechupload`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nonteacher`
--
ALTER TABLE `nonteacher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_id` (`unique_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `HASH` (`HASH`);

--
-- Indexes for table `pm`
--
ALTER TABLE `pm`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sc`
--
ALTER TABLE `sc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ss`
--
ALTER TABLE `ss`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_id` (`unique_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `hash` (`hash`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_id` (`unique_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `HASH` (`HASH`);

--
-- Indexes for table `upload`
--
ALTER TABLE `upload`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `atdsp`
--
ALTER TABLE `atdsp`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;
--
-- AUTO_INCREMENT for table `atmc`
--
ALTER TABLE `atmc`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `civilupload`
--
ALTER TABLE `civilupload`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cmpnupload`
--
ALTER TABLE `cmpnupload`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `etrxupload`
--
ALTER TABLE `etrxupload`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `extcupload`
--
ALTER TABLE `extcupload`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `gupload`
--
ALTER TABLE `gupload`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `itupload`
--
ALTER TABLE `itupload`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mechupload`
--
ALTER TABLE `mechupload`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `nonteacher`
--
ALTER TABLE `nonteacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=246;
--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `upload`
--
ALTER TABLE `upload`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
