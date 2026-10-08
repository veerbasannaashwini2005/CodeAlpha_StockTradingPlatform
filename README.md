# Stock Trading Platform

A Java-based Stock Trading Platform that simulates a basic stock market environment. The application allows users to view market data, buy and sell stocks, manage their portfolio, track transactions, calculate portfolio performance, and save/load portfolio data using file handling.

## Features

- View available stock market data
- Buy stocks using available balance
- Sell stocks from the user's portfolio
- Track stock holdings and quantities
- View current portfolio value
- Calculate portfolio profit/loss
- View complete transaction history
- Display user account details
- Save portfolio data to a file
- Save transaction history to a file
- Load previously saved portfolio data
- Automatically restore portfolio data when the application starts
- Input validation for invalid choices and quantities

## Technologies Used

- **Java**
- **Object-Oriented Programming (OOP)**
- **Java Collections Framework**
- **File I/O**
- **Exception Handling**
- **Java Date and Time API**
- **Command-Line Interface (CLI)**

## OOP Concepts Used

### Encapsulation
Private variables and public getter/setter methods are used to protect and manage data.

### Classes and Objects
The application is divided into multiple classes representing different entities such as users, stocks, portfolios, and transactions.

### Abstraction
Different responsibilities are separated into individual classes to make the application easier to understand and maintain.

## Project Structure

```text
CodeAlpha_StockTradingPlatform/
│
├── src/
│   ├── Main.java
│   ├── Stock.java
│   ├── User.java
│   ├── Portfolio.java
│   ├── Transaction.java
│   ├── Market.java
│   ├── TradingPlatform.java
│   └── DataManager.java
│
├── data/
│   ├── portfolio.txt
│   └── transactions.txt
│
├── out/
│
├── README.md
└── .gitignore
```

## Class Description

| Class | Description |
|---|---|
| `Main` | Starts the Stock Trading Platform |
| `Stock` | Represents an individual stock |
| `User` | Stores user account and balance information |
| `Portfolio` | Manages stock holdings and investment values |
| `Transaction` | Represents buy and sell transactions |
| `Market` | Stores and displays available stocks |
| `TradingPlatform` | Handles the main menu and trading operations |
| `DataManager` | Saves and loads portfolio and transaction data |

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/CodeAlpha_StockTradingPlatform.git
```

### 2. Open the Project

Open the project folder in VS Code or another Java-supported IDE.

### 3. Compile the Program

From the project root directory:

```bash
javac -d out src\*.java
```

### 4. Run the Application

```bash
java -cp out Main
```

## Main Menu

```text
==========================================
        STOCK TRADING PLATFORM
==========================================
1. View Market Data
2. Buy Stock
3. Sell Stock
4. View Portfolio
5. Transaction History
6. Portfolio Performance
7. User Details
8. Save Portfolio
9. Load Saved Data
10. Exit
==========================================
```

## Sample Market Data

```text
================ MARKET DATA ================
Symbol     Company                   Price
----------------------------------------------
TCS        Tata Consultancy Services Rs. 3800.00
ITC        ITC Limited               Rs. 520.00
INFY       Infosys Limited           Rs. 1650.00
HDFCBANK   HDFC Bank                 Rs. 1750.00
RELIANCE   Reliance Industries       Rs. 2900.00
```

## Portfolio Performance

The application calculates:

- Cash Balance
- Amount Invested
- Current Stock Value
- Total Portfolio Value
- Profit / Loss

Example:

```text
========== PORTFOLIO PERFORMANCE ==========

Cash Balance       : Rs. 94550.00
Amount Invested    : Rs. 5450.00
Current Stock Value: Rs. 5450.00
Total Portfolio    : Rs. 100000.00
Profit / Loss      : Rs. 0.00
```

## File Persistence

The application uses Java File I/O to store data locally.

### `portfolio.txt`

Stores:

- User ID
- User name
- Account balance
- Stock holdings
- Investment values

### `transactions.txt`

Stores:

- Transaction type
- Stock symbol
- Quantity
- Transaction price

Saved data can be loaded again when the application is restarted.

## Validation

The application handles several invalid inputs, including:

- Invalid menu choices
- Invalid stock symbols
- Invalid quantities
- Negative or zero quantities
- Selling more stocks than currently owned
- Buying stocks when the balance is insufficient

## Future Enhancements

Possible future improvements include:

- Real-time stock market API integration
- User login and authentication
- Database integration
- Graphical User Interface
- Live stock price updates
- Multiple user accounts
- Advanced portfolio performance charts
- Stock price history
- Watchlist functionality

## Project Objective

The objective of this project is to demonstrate how Object-Oriented Programming concepts and Java File I/O can be used to develop a basic stock trading simulation system.

## Author

**Ashwini**

Developed as part of the **CodeAlpha Java Internship**.