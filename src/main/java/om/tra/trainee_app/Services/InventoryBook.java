package om.tra.trainee_app.Services;

public class InventoryBook {


        private int bookId;
        private String title;
        private double price;
        private int stockCount;

        public InventoryBook(int bookId, String title, double price, int stockCount) {
            this.bookId = bookId;
            this.title = title;
            this.price = price;
            this.stockCount = stockCount;
        }

        public int getBookId() {
            return bookId;
        }

        public String getTitle() {
            return title;
        }

        public double getPrice() {
            return price;
        }

        public int getStockCount() {
            return stockCount;
        }
    }

