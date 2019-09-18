package elasticsearchtest;

import cn.suse.elastticsearch.ItemRepository;
import cn.suse.elastticsearch.ElasticsearchApplication;
import cn.suse.pojo.Item;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest(classes = ElasticsearchApplication.class)
@RunWith(SpringRunner.class)
public class Etest {
    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private ItemRepository repository;
    @Test
    public void testPut(){
        // 创建索引，会根据Item类的@Document注解信息来创建
        template.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        template.putMapping(Item.class);
    }
    @Test
    public void testCreate(){
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        this.repository.save(item);
    }
    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        // 接收对象集合，实现批量新增
        repository.saveAll(list);
    }
    @Test
    public void testQuery(){
        Optional<Item> optional = this.repository.findById(1l);
        System.out.println(optional.get());
    }

    @Test
    public void testFind(){
        // 查询全部，并按照价格降序排序
        Iterable<Item> items = this.repository.findAll(Sort.by("price").descending());
        List<Item> list = new ArrayList();
        items.forEach(item -> {
            list.add(item);
        });
        List<Item> collect = list.stream().filter((i) -> i.getPrice() > 4000).collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
    @Test
    public void indexList2() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        repository.saveAll(list);
    }
    @Test
    public void queryByPriceBetween(){
        //List<Item> list = this.repository.findByPriceBetween(2000.00, 3500.00);
        List<Item> list = this.repository.findByTitle("小米");
        list.forEach(System.out::println);
    }
    @Test
    public void testNativeSearch(){
        //构建自定义查询构建器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //添加查询条件
        builder.withQuery(QueryBuilders.matchQuery("category","手机"));
        //添加分页条件,页码从零计数
        builder.withPageable(PageRequest.of(1,2));
        //添加排序
        builder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        //执行查询
        Page<Item> search = this.repository.search(builder.build());
        System.out.println(search.getTotalElements());
        System.out.println(search.getTotalPages());
        search.getContent().forEach(System.out::println);


    }
    @Test
    public void testAgg(){
        //构建自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand"));
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{},null));
        //查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.repository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brandAgg的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms)aggPage.getAggregation("brandAgg");
        //获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        buckets.forEach(bucket -> {
            //获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            //获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        });
    }
    @Test
    public void testSubAgg(){
        //构建自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand")
            .subAggregation(AggregationBuilders.avg("priceAvg").field("price"))//在品牌聚合桶内进行嵌套聚合，求平均值
        );
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{},null));
        //查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.repository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brandAgg的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms)aggPage.getAggregation("brandAgg");
        //获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        buckets.forEach(bucket -> {
            //获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            //获取桶中的文档数量
            System.out.println(bucket.getDocCount());
            // 获取子聚合结果：
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceAvg");
            System.out.println("平均售价：" + avg.getValue());
        });
    }


}
