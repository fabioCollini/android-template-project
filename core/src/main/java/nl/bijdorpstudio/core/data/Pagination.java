package nl.bijdorpstudio.core.data;

public class Pagination
{
    private final int numberOfPages;

    private final int currentPage;

    public Pagination( final int currentPage, final int numberOfPages )
    {
        this.numberOfPages = numberOfPages;
        this.currentPage = currentPage;
    }

    public int getNumberOfPages()
    {
        return numberOfPages;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public int getNextPage()
    {
        return currentPage + 1;
    }

    public boolean isLastPage()
    {
        return currentPage == numberOfPages - 1;
    }
}
