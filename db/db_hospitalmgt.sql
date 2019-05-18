-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 01, 2019 at 08:19 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_hospitalmgt`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_doctors`
--

CREATE TABLE `tbl_doctors` (
  `doctor_id` int(11) NOT NULL,
  `hos_id` int(10) NOT NULL,
  `doc_name` varchar(100) NOT NULL,
  `doc_specialist` varchar(50) NOT NULL,
  `doc_holi` varchar(50) NOT NULL,
  `doc_image` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_doctors`
--

INSERT INTO `tbl_doctors` (`doctor_id`, `hos_id`, `doc_name`, `doc_specialist`, `doc_holi`, `doc_image`) VALUES
(1, 2, 'Arjun', '', '', 'Arjun.JPEG'),
(2, 2, 'Arjun', '', 'sunday', 'Arjun.JPEG'),
(3, 2, 'Arjun', '', 'sunday', 'Arjun.JPEG'),
(4, 2, 'Arjun', 'physiotherapy', 'sunday', 'Arjun.JPEG'),
(5, 2, 'Havila john', 'ENT', 'sunday', 'Havila john.JPEG');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_facilities`
--

CREATE TABLE `tbl_facilities` (
  `facility_id` int(11) NOT NULL,
  `hos_id` int(10) NOT NULL,
  `caption` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `image` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_facilities`
--

INSERT INTO `tbl_facilities` (`facility_id`, `hos_id`, `caption`, `description`, `image`) VALUES
(1, 0, 'facility1', 'niceeee', 'facility1.JPEG'),
(2, 2, 'facility1', 'niceeee', 'facility1.JPEG');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_hospital`
--

CREATE TABLE `tbl_hospital` (
  `hos_id` int(11) NOT NULL,
  `hos_name` varchar(100) NOT NULL,
  `hos_place` varchar(50) NOT NULL,
  `hos_contact` bigint(20) NOT NULL,
  `hos_state` varchar(50) NOT NULL,
  `hos_district` varchar(50) NOT NULL,
  `hos_image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_hospital`
--

INSERT INTO `tbl_hospital` (`hos_id`, `hos_name`, `hos_place`, `hos_contact`, `hos_state`, `hos_district`, `hos_image`) VALUES
(4, 'ashwini', 'hos_place', 9539206718, 'Kerala', 'Thrissur', 'ashwini.JPEG');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_hospital_reg`
--

CREATE TABLE `tbl_hospital_reg` (
  `hos_reg_id` int(11) NOT NULL,
  `hos_reg_name` varchar(50) NOT NULL,
  `hos_reg_email` varchar(50) NOT NULL,
  `hos_reg_place` varchar(50) NOT NULL,
  `hos_reg_district` varchar(50) NOT NULL,
  `hos_reg_state` varchar(50) NOT NULL,
  `hos_reg_contact` bigint(50) NOT NULL,
  `hos_reg_password` varchar(50) NOT NULL,
  `hos_image` varchar(100) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_hospital_reg`
--

INSERT INTO `tbl_hospital_reg` (`hos_reg_id`, `hos_reg_name`, `hos_reg_email`, `hos_reg_place`, `hos_reg_district`, `hos_reg_state`, `hos_reg_contact`, `hos_reg_password`, `hos_image`, `status`) VALUES
(2, 'ashwini', 'ashwini@gmail.com', 'thrissur', 'Thrissur', 'Kerala', 9539206718, '1234', 'ashwini.JPEG', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_register`
--

CREATE TABLE `tbl_register` (
  `user_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` bigint(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_register`
--

INSERT INTO `tbl_register` (`user_id`, `username`, `email`, `phone`, `password`) VALUES
(1, 'musthafa', 'musthafa@gmail.com', 9534206718, '1234');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_services`
--

CREATE TABLE `tbl_services` (
  `service_id` int(11) NOT NULL,
  `hos_id` int(10) NOT NULL,
  `caption` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `image` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_services`
--

INSERT INTO `tbl_services` (`service_id`, `hos_id`, `caption`, `description`, `image`) VALUES
(1, 2, 'Ambulance', 'nicee', 'Ambulance.JPEG');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_doctors`
--
ALTER TABLE `tbl_doctors`
  ADD PRIMARY KEY (`doctor_id`);

--
-- Indexes for table `tbl_facilities`
--
ALTER TABLE `tbl_facilities`
  ADD PRIMARY KEY (`facility_id`);

--
-- Indexes for table `tbl_hospital`
--
ALTER TABLE `tbl_hospital`
  ADD PRIMARY KEY (`hos_id`);

--
-- Indexes for table `tbl_hospital_reg`
--
ALTER TABLE `tbl_hospital_reg`
  ADD PRIMARY KEY (`hos_reg_id`);

--
-- Indexes for table `tbl_register`
--
ALTER TABLE `tbl_register`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `tbl_services`
--
ALTER TABLE `tbl_services`
  ADD PRIMARY KEY (`service_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_doctors`
--
ALTER TABLE `tbl_doctors`
  MODIFY `doctor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_facilities`
--
ALTER TABLE `tbl_facilities`
  MODIFY `facility_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_hospital`
--
ALTER TABLE `tbl_hospital`
  MODIFY `hos_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_hospital_reg`
--
ALTER TABLE `tbl_hospital_reg`
  MODIFY `hos_reg_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_register`
--
ALTER TABLE `tbl_register`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_services`
--
ALTER TABLE `tbl_services`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
