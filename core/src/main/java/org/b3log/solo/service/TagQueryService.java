/*
 * Copyright (c) 2009, 2010, 2011, 2012, 2013, B3log Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.b3log.solo.service;


import java.util.List;
import javax.inject.Inject;
import org.b3log.latke.Keys;
import org.b3log.latke.logging.Level;
import org.b3log.latke.logging.Logger;
import org.b3log.latke.repository.Query;
import org.b3log.latke.repository.RepositoryException;
import org.b3log.latke.service.ServiceException;
import org.b3log.latke.service.annotation.Service;
import org.b3log.latke.util.CollectionUtils;
import org.b3log.solo.model.Tag;
import org.b3log.solo.repository.TagRepository;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Tag query service.
 *
 * @author <a href="mailto:DL88250@gmail.com">Liang Ding</a>
 * @version 1.0.0.3, Jun 28, 2012
 * @since 0.4.0
 */
@Service
public class TagQueryService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TagQueryService.class.getName());

    /**
     * Tag repository.
     */
    @Inject
    private TagRepository tagRepository;

    /**
     * Gets a tag by the specified tag title.
     *
     * @param tagTitle the specified tag title
     * @return for example,
     * <pre>
     * {
     *     "tag": {
     *         "oId": "",
     *         "tagTitle": "",
     *         "tagReferenceCount": int,
     *         "tagPublishedRefCount": int
     *     }
     * }
     * </pre>, returns {@code null} if not found
     * @throws ServiceException service exception
     */
    public JSONObject getTagByTitle(final String tagTitle) throws ServiceException {
        try {
            final JSONObject ret = new JSONObject();

            final JSONObject tag = tagRepository.getByTitle(tagTitle);

            if (null == tag) {
                return null;
            }

            ret.put(Tag.TAG, tag);

            LOGGER.log(Level.DEBUG, "Got an tag[title={0}]", tagTitle);

            return ret;
        } catch (final RepositoryException e) {
            LOGGER.log(Level.ERROR, "Gets an article failed", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Gets the count of tags.
     * 
     * @return count of tags
     * @throws ServiceException service exception
     */
    public long getTagCount() throws ServiceException {
        try {
            return tagRepository.count();
        } catch (final RepositoryException e) {
            LOGGER.log(Level.ERROR, "Gets tags failed", e);

            throw new ServiceException(e);
        }
    }

    /**
     * Gets all tags.
     *
     * @return for example,
     * <pre>
     * [
     *     {"tagTitle": "", "tagReferenceCount": int, ....},
     *     ....
     * ]
     * </pre>, returns an empty list if not found
     * @throws ServiceException service exception
     */
    public List<JSONObject> getTags() throws ServiceException {
        try {
            final Query query = new Query().setPageCount(1);

            final JSONObject result = tagRepository.get(query);
            final JSONArray tagArray = result.optJSONArray(Keys.RESULTS);

            return CollectionUtils.jsonArrayToList(tagArray);
        } catch (final RepositoryException e) {
            LOGGER.log(Level.ERROR, "Gets tags failed", e);

            throw new ServiceException(e);
        }
    }

    /**
     * Sets the tag repository with the specified tag repository.
     * 
     * @param tagRepository the specified tag repository
     */
    public void setTagRepository(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
}
