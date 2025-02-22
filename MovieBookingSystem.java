import java.util.Scanner;

// Movie class to represent a movie with a name, genre, and duration
class Movie {
    private String movieName;
    private String genre;
    private int duration; // Duration in minutes

    public Movie(String movieName, String genre, int duration) {
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return movieName + " (Genre: " + genre + ", Duration: " + duration + " minutes)";
    }
}

// Theater class to represent a theater and its available seats
class Theater {
    private final String theaterName;  // Marked as final
    private int totalSeats;
    private int availableSeats;
    private Movie currentMovie;

    public Theater(String theaterName, int totalSeats, Movie currentMovie) {
        this.theaterName = theaterName;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats; // Initially, all seats are available
        this.currentMovie = currentMovie;
    }

    public boolean isSeatAvailable() {
        return availableSeats > 0;
    }

    public void bookSeat() {
        if (isSeatAvailable()) {
            availableSeats--;
            System.out.println("Booking successful! Remaining seats: " + availableSeats);
        } else {
            System.out.println("Sorry, no available seats!");
        }
    }

    public void showTheaterDetails() {
        System.out.println("Theater: " + theaterName);
        System.out.println("Currently Playing: " + currentMovie);
        System.out.println("Available Seats: " + availableSeats + "/" + totalSeats);
    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }
}

// Booking class to handle the booking logic
class Booking {
    private Theater theater;

    public Booking(Theater theater) {
        this.theater = theater;
    }

    public void bookTicket() {
        if (theater.isSeatAvailable()) {
            theater.bookSeat();
        } else {
            System.out.println("No seats available to book.");
        }
    }

    public void showTheaterInfo() {
        theater.showTheaterDetails();
    }
}

public class MovieBookingSystem {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Taking input for movie details
        System.out.print("Enter the movie name: ");
        String movieName = scanner.nextLine();

        System.out.print("Enter the genre of the movie: ");
        String genre = scanner.nextLine();

        System.out.print("Enter the movie duration (in minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Create a Movie object
        Movie movie = new Movie(movieName, genre, duration);

        // Taking input for theater details
        System.out.print("Enter the theater name: ");
        String theaterName = scanner.nextLine();

        System.out.print("Enter the total number of seats in the theater: ");
        int totalSeats = scanner.nextInt();

        // Create a Theater object with the movie and seats
        Theater theater = new Theater(theaterName, totalSeats, movie);

        // Create a Booking object
        Booking booking = new Booking(theater);

        // Display initial theater details
        System.out.println("\nInitial Theater Details:");
        booking.showTheaterInfo();

        // Loop for booking process
        while (true) {
            System.out.println("\nDo you want to book a ticket? (yes/no): ");
            String userChoice = scanner.next();

            if (userChoice.equalsIgnoreCase("yes")) {
                booking.bookTicket(); // Book a seat
            } else if (userChoice.equalsIgnoreCase("no")) {
                System.out.println("Thank you for visiting. Have a nice day!");
                break; // Exit the loop and end program
            } else {
                System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            }

            // Display updated theater details after booking a ticket
            booking.showTheaterInfo();
        }

        scanner.close(); // Close the scanner to prevent resource leak
    }
}
