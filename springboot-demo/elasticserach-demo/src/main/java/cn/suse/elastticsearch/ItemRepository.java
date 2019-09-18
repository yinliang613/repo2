package cn.suse.elastticsearch;

import cn.suse.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    public List<Item> findByTitle(String title);
    public List<Item> findByPriceBetween(Double d1,Double d2);
}
