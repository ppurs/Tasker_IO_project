package com.example.project_tasker;

import java.util.ArrayList;

class Project extends StructuralElement {
    ArrayList<Category> categories;

    public Project() {
        categories = new ArrayList<>();
    }

    public Project(String name, String description ) {
        super(name, description);
        this.categories = new ArrayList<>();
    }

    boolean validation( String categoryName ) {
        for ( Category temp : categories) {
            if (temp.name.equals( categoryName ) ) {
                return false;
            }
        }
        return true;
    }

    boolean addCategory( String categoryName, String categoryDescription, int color ) {
        boolean temp = validation( categoryName );

        if( temp )
            categories.add( new Category( categoryName, categoryDescription ) );
            categories.get( categories.size() - 1 ).setColor( color );

        return temp;
    }

    void deleteCategory( int index ){
        categories.remove( index );
    }

    int[] generateStatistics()
    {
        int totalTasks = 0;
        int totalCards = 0;
        int totalCategories = 0;
        int tasksFinished = 0;
        int tasksUnfinished = 0;

        for(Category category : categories)
        {
            totalCategories++;

            for(Card card : category.cards)
            {
                totalCards++;

                for(Task task : card.tasks)
                {
                    totalTasks++;

                    if(task.getStatus())
                    {
                        tasksFinished++;
                    }
                    else
                    {
                        tasksUnfinished++;
                    }
                }
            }
        }

        int[] stats = {totalTasks, totalCards, totalCategories, tasksFinished, tasksUnfinished};
        return stats;
    }

}
