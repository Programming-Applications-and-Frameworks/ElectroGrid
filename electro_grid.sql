-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 03:13 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electro_grid`
--
CREATE DATABASE IF NOT EXISTS `electro_grid` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `electro_grid`;

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL,
  `bill_number` varchar(40) NOT NULL,
  `prev_read` varchar(40) NOT NULL,
  `cur_read` varchar(40) NOT NULL,
  `bill_amount` varchar(40) NOT NULL,
  `due_amount` varchar(40) NOT NULL,
  `billing_date` varchar(40) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `branch_id` int(10) NOT NULL,
  `mapLocationCode` varchar(10) NOT NULL,
  `addressLine1` varchar(256) NOT NULL,
  `addressLine2` varchar(256) DEFAULT NULL,
  `city` varchar(128) NOT NULL,
  `zipCode` int(11) NOT NULL,
  `phone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`branch_id`, `mapLocationCode`, `addressLine1`, `addressLine2`, `city`, `zipCode`, `phone`) VALUES
(2, 'WW2C+9J9', 'B47, Sri Jayawardenepura Kotte', 'Battaramulla', 'Colombo', 10120, 112864085),
(3, 'WXQ5+773', 'Welivita', '', 'Colombo', 10640, 112537975),
(4, 'XXPG+VXF', 'Makola - Udupila Rd', 'Mawaramandiya', 'Colombo', 11650, 112977185),
(5, 'WVJ9+V73', 'Maligawatte Rd', 'Colombo ', 'Colombo', 1000, 114498498),
(6, '2X47+X2Q', 'Maladolawaththa Rd', 'Kadawatha', 'Gampaha', 11850, 112925271),
(7, 'RV6J+X8H', 'Dehiwala-Mount Lavinia', '', 'Colombo', 10350, 112625371),
(8, 'R2Q3+88W ', 'Homagama Branch, B452', 'Homagama', 'Colombo', 10206, 112855307),
(9, 'WVMJ+9JQ', 'Kent Road', 'Colombo ', 'Colombo ', 900, 112690393),
(10, '2WQ2+MF5', 'CEB Central Workshop Rd', 'Ragama', 'Gampaha', 11010, 114356721),
(11, 'RV5V+353', 'Thelawala Rd', 'Moratuwa', 'Colombo', 10400, 777686686);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `telePhoneNumber` int(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `District` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `readingCurrent` varchar(30) NOT NULL,
  `nic` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `Payment_ID` int(11) NOT NULL,
  `TransactionID` varchar(50) NOT NULL,
  `Paymentmethod` varchar(50) NOT NULL,
  `Amount` double(12,2) NOT NULL,
  `Bill_No` int(20) NOT NULL,
  `Bank_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`Payment_ID`, `TransactionID`, `Paymentmethod`, `Amount`, `Bill_No`, `Bank_id`) VALUES
(16, 'Thushan', 'Chanka', 15000.00, 12345648, 'HNB'),
(17, 'Malar', ' Tap', 2999.00, 393733, 'BOC'),
(18, 'Sharmilan', 'Nilakshana', 10000.00, 200034678, 'Commercial Bank'),
(19, 'Sharmilan', 'Nilu', 2000.00, 200034678, 'Commercial Bank'),
(20, 'Malar', ' Tap', 2999.00, 393733, 'BOC'),
(21, 'souji', 'kishomi', 10000.00, 2333332, 'peoples'),
(22, 'souji', 'kishomi', 10000.00, 2333332, 'peoples');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(10) NOT NULL,
  `firstname` varchar(64) NOT NULL,
  `lastname` varchar(64) NOT NULL,
  `nic` varchar(16) NOT NULL,
  `phone` varchar(32) NOT NULL,
  `branch_id` int(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `firstname`, `lastname`, `nic`, `phone`, `branch_id`, `email`, `password`) VALUES
(20, 'mark', 'antony', '1994544565', '0115154555', 8, 'markantony@gmail.com', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `foreign_key_user_id` (`user_id`);

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`branch_id`),
  ADD UNIQUE KEY `branch_code` (`mapLocationCode`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`Payment_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `uniquie_email` (`email`),
  ADD UNIQUE KEY `uniquie_nic` (`nic`),
  ADD UNIQUE KEY `unique_phone` (`phone`),
  ADD KEY `foreign_key_branch_id` (`branch_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `branch_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `Payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `foreign_key_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `foreign_key_branch_id` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
