-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: db:3306
-- Generation Time: May 05, 2026 at 05:38 AM
-- Server version: 8.4.8
-- PHP Version: 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


--
-- Database: `sales`
--

-- --------------------------------------------------------

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`id`, `customer_id`, `status_id`, `amount`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 2, 14000, '2026-05-05 01:25:08.000000', NULL, NULL);

-- --------------------------------------------------------

--
-- Dumping data for table `sales_details`
--

INSERT INTO `sales_details` (`id`, `sale_id`, `description`, `sku`, `quantity`, `unit_price`) VALUES
(1, 1, 'Foo', '1234', 2, 5000),
(2, 1, 'Bar', '4321', 1, 4000);

-- --------------------------------------------------------

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sales_details`
--
ALTER TABLE `sales_details`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
