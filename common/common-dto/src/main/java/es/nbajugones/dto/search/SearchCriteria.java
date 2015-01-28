package es.nbajugones.dto.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Search criteria for generic DB queries.
 *
 * @author Ignacio Blanco
 */
public class SearchCriteria
{

    /**
     * Order by.
     */
    private List<OrderByCriterion> orderByCriteria = new ArrayList<OrderByCriterion>();

    /**
     * First result.
     */
    private Integer startIndex;

    /**
     * Max results.
     */
    private Integer batchSize;

    /**
     * Filters.
     */
    private List<FilterCriterion> filterCriteria = new ArrayList<FilterCriterion>();

    /**
     * Or clauses.
     */
    private List<List<FilterCriterion>> orClauses = new ArrayList<List<FilterCriterion>>();

    /**
     * Adds an OR clause.
     *
     * @param orFilters filters of clause
     */
    public void addOrClause(List<FilterCriterion> orFilters)
    {
        orClauses.add(orFilters);
    }

    /**
     * Add order.
     *
     * @param column    Column to order by
     * @param direction Direction of order
     */
    public void addOrder(String column, OrderByCriterion.OrderDirection direction)
    {
        getOrderByCriteria().add(new OrderByCriterion(column, direction));
    }

    /**
     * Add new filter.
     *
     * @param column Column to filter by
     * @param value  Expected value
     * @param type   type of comparison
     */
    public void addFilter(String column, Object value, FilterCriterion.FilterType type)
    {
        getFilterCriteria().add(new FilterCriterion(column, value, type));
    }

    /**
     * Returns a new filter.
     * @param column Column to filter by
     * @param value  Expected value
     * @param type   type of comparison
     * @return filter
     */
    public FilterCriterion createFilter(String column, Object value, FilterCriterion.FilterType type)
    {
        return new FilterCriterion(column, value, type);
    }

    /**
     * Filter criterion.
     */
    public static final class FilterCriterion
    {
        /**
         * Column name.
         */
        private String columnName;

        /**
         * Value.
         */
        private Object value;
        
        /** The value2. */
        private Object value2;

		/**
         * Type.
         */
        private FilterType type;

        /**
         * FilterType for filtering classes.
         */
        public enum FilterType
        {
            /**
             * equals.
             */
            EQUALS,
            /**
             * not equals.
             */
            NOT_EQUALS,
            /**
             * like.
             */
            LIKE, 
            
            /**
             * Less than.
             */
            LESS,
            /**
             * More than.
             */
            MORE,            
            /** The between. */
            BETWEEN;
        }

        /**
         * Full constructor.
         *
         * @param columnName Column to filter by
         * @param value      Expected value
         * @param type       type of comparison
         */
        public FilterCriterion(String columnName, Object value, FilterType type)
        {
            this.columnName = columnName;
            this.value = value;
            this.type = type;
        }
        
        /**
         * Instantiates a new filter criterion.
         *
         * @param columnName the column name
         * @param value the value
         * @param value2 the value2
         * @param type the type
         */
        public FilterCriterion(String columnName, Object value, Object value2, FilterType type)
        {
            this.columnName = columnName;
            this.value = value;
            this.type = type;
            this.value2 = value2;
        }

        /**
         * Sets new Type..
         *
         * @param type New value of Type..
         */
        public void setType(FilterType type)
        {
            this.type = type;
        }

        /**
         * Sets new Column name..
         *
         * @param columnName New value of Column name..
         */
        public void setColumnName(String columnName)
        {
            this.columnName = columnName;
        }

        /**
         * Gets Value..
         *
         * @return Value of Value..
         */
        public Object getValue()
        {
            return value;
        }

        /**
         * Gets Type..
         *
         * @return Value of Type..
         */
        public FilterType getType()
        {
            return type;
        }

        /**
         * Gets Column name..
         *
         * @return Value of Column name..
         */
        public String getColumnName()
        {
            return columnName;
        }

        /**
         * Sets new Value..
         *
         * @param value New value of Value..
         */
        public void setValue(Object value)
        {
            this.value = value;
        }
        
        /**
         * Gets the value2.
         *
         * @return the value2
         */
        public Object getValue2() 
        {
    		return value2;
    	}

    	/**
	     * Sets the value2.
	     *
	     * @param value2 the new value2
	     */
	    public void setValue2(Object value2) 
	    {
    		this.value2 = value2;
    	}
    }

    /**
     * Order by criterion.
     */
    public static final class OrderByCriterion
    {

        /**
         * Order by direction.
         */
        public enum OrderDirection
        {
            /**
             * Ascendant.
             */
            ASC,
            /**
             * Descendant.
             */
            DESC
        }

        /**
         * Column to order by.
         */
        String column;

        /**
         * asc or desc.
         */
        OrderDirection direction;

        /**
         * Constructor.
         *
         * @param column    Column
         * @param direction Direction enum
         */
        private OrderByCriterion(String column, OrderDirection direction)
        {
            this.column = column;
            this.direction = direction;
        }

        /**
         * Sets new column.
         *
         * @param column New value of column.
         */
        public void setColumn(String column)
        {
            this.column = column;
        }

        /**
         * Gets column.
         *
         * @return Value of column.
         */
        public String getColumn()
        {
            return column;
        }

        /**
         * Sets new direction.
         *
         * @param direction New value of direction.
         */
        public void setDirection(OrderDirection direction)
        {
            this.direction = direction;
        }

        /**
         * Gets direction.
         *
         * @return Value of direction.
         */
        public OrderDirection getDirection()
        {
            return direction;
        }
    }


    /**
     * Gets orderByCriteria.
     *
     * @return Value of orderByCriteria.
     */
    public List<OrderByCriterion> getOrderByCriteria()
    {
        return orderByCriteria;
    }

    /**
     * Sets new startIndex.
     *
     * @param startIndex New value of startIndex.
     */
    public void setStartIndex(Integer startIndex)
    {
        this.startIndex = startIndex;
    }

    /**
     * Sets new batchSize.
     *
     * @param batchSize New value of batchSize.
     */
    public void setBatchSize(Integer batchSize)
    {
        this.batchSize = batchSize;
    }

    /**
     * Gets batchSize.
     *
     * @return Value of batchSize.
     */
    public Integer getBatchSize()
    {
        return batchSize;
    }

    /**
     * Gets startIndex.
     *
     * @return Value of startIndex.
     */
    public Integer getStartIndex()
    {
        return startIndex;
    }


    /**
     * Gets Filters..
     *
     * @return Value of Filters..
     */
    public List<FilterCriterion> getFilterCriteria()
    {
        return filterCriteria;
    }

    /**
     * Gets Or clauses..
     *
     * @return Value of Or clauses..
     */
    public List<List<FilterCriterion>> getOrClauses()
    {
        return orClauses;
    }
}
