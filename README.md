# Beauty Salon Management: Java Client–Server App

A desktop application for running a beauty salon: staff manage clients, beauticians, work shifts and **appointment records** with itemised services and prices.

It is built as a classic **three-tier client–server system in pure Java**: a Swing desktop client talks to a multithreaded socket server, and the server works with MySQL through a generic JDBC database broker. No frameworks are used, so every layer is written by hand.

> Academic project for the *Software Design* course at the Faculty of Organizational Sciences, University of Belgrade.

## Features

- **Staff login**: the client application requires authentication against the `radnik` (staff) table.
- **Clients**: create, edit, delete and search.
- **Beauticians**: create, edit and delete. Each beautician belongs to a service category (nails, hair, facial…).
- **Work shifts**: add shifts and list them.
- **Appointment records**: create, edit, delete and search records. Each record links a client, a beautician and the staff member who entered it, and holds a list of line items (service, duration, price) with an automatically calculated total.
- **Server GUI**: start and stop the server, and configure the database connection from a form.

## Architecture

```
┌──────────────┐   TCP socket (port 9000)    ┌───────────────────────────────┐      JDBC     ┌────────┐
│  client      │  ── serialized Zahtev ──►   │  server                       │  ──────────►  │ MySQL  │
│  Swing GUI   │  ◄── serialized Odgovor ──  │  thread per connected client  │               └────────┘
└──────────────┘                             └───────────────────────────────┘
        └──────────────── common (shared domain model + protocol) ────────────────┘
```

The project is split into three NetBeans modules:

| Module | Responsibility |
|---|---|
| `common` | Domain classes (`Klijent`, `Kozmeticar`, `EvidencijaTermina`, …), the request/response protocol (`Zahtev`, `Odgovor`, `Operacija` enum), and socket send/receive helpers |
| `server` | Socket server, one handler thread per client, system operations, database broker, server admin GUI |
| `client` | Swing forms, form controllers, a coordinator for navigation, and a communication singleton |

**Design patterns and techniques**
- **Template Method**: every system operation extends `ApstraktnaGenerickaOperacija`, which runs a fixed sequence: check preconditions → start transaction → execute → commit, or roll back on error.
- **Generic repository / DB broker**: `BrokerBP` builds SQL (`SELECT`, `INSERT`, `UPDATE`, `DELETE`) for any class implementing `ApstraktniDomenskiObjekat`, which supplies its own table name, columns, values and key. One broker therefore covers every entity.
- **Singleton**: the server controller, the DB connection factory, the configuration, and the client's communication layer.
- **MVC on the client**: forms hold only UI code, controllers handle events and calls to the server, and custom `TableModel`s bind data to tables.
- **Multithreading**: the server accepts connections in its own thread and gives each client a dedicated handler thread.
- **Transactions**: auto-commit is off, and each system operation is one unit of work. This matters for master–detail saves such as an appointment record with its items.

## Tech stack

Java (Swing, sockets, JDBC, serialization) · MySQL · NetBeans / Apache Ant

## Getting started

### Prerequisites
- JDK 21+ (the project targets Java 23 in NetBeans and also compiles with 21)
- MySQL 8
- NetBeans (recommended) or Apache Ant
- [MySQL Connector/J 9.1.0](https://dev.mysql.com/downloads/connector/j/): put `mysql-connector-j-9.1.0.jar` in `server/lib/`

### 1. Database

```bash
mysql -u root -p < database/schema.sql
```

This creates the `0_seminarski` database with sample data, including a demo login of **admin / admin**.
Edit `server/config/config.properties` if your MySQL username or password differ.

### 2. Build and run (NetBeans)

1. Open the three projects `common`, `server` and `client`.
2. **Clean & Build** `common` first, because the other two depend on its jar.
3. Run `server` and click **Start** in the server window.
4. Run `client` and log in.

With Ant from the command line, run `ant jar` in `common`, then `ant run` in `server` and in `client`.

## Possible improvements

This is a course project, and these are things I would change in a production version:
- Hash staff passwords (e.g. BCrypt) instead of storing them in plain text.
- Use `PreparedStatement` parameters everywhere instead of building SQL strings, to prevent SQL injection.
- Read the server port from configuration (it is currently fixed at 9000).
- Add unit tests for the system operations.

## Author

**Anja Perović**, student at the Faculty of Organizational Sciences, University of Belgrade
